
(* Type d'un arbre markdown, un document est une listes d'arbre
   markdown, et chaque noeud de l'arbre contient également une liste
   de sous-arbre. *)
type tree =
  | Texte of string
  | ForcedNL
  | Titre of int * tree list
  | HLigne (* une ligne horizontale *)
  | Gras of tree list
  | TresGras of tree list
  | Code of string (* du code dans le texte (le texte doit apparaître tel quel *)
  | ParagCode of string (* un paragraphe de code *)
  | Lien of (tree list*string) (* t=le texte à afficher, string=url *)
  | Paragraphe of tree list
  | List of (tree list) list
  | Enum of (tree list) list


(* Le type retourné par le module Omd est trop compliqué, on le
   traduit vers le type element ci-dessus. *)
let rec omd_to_md_list (omd:Omd.element list): tree list =
  match omd with
  | [] -> []
  | e::omd' -> omd_to_md e :: omd_to_md_list omd'
and omd_to_md (omd:Omd.element): tree =
  let module O = Omd in
  match omd with
  | O.X _ -> failwith "Extensions not supported"
  | O.Blockquote _q -> failwith "Quoted Block not supported"
  | O.Ref (_rc, _name, _text, _fallback) -> failwith "Ref not supported"
  | O.Img (_alt, _src, _title) -> failwith "image not supported"
  | O.Img_ref (_rc, _name, _alt, _fallback) -> failwith "imageRef not supported"
  | O.Raw _t -> failwith "Raw not supported"
  | O.Raw_block _t -> failwith "Raw_block not supported"
  | O.Br -> failwith "forced newlines not supported"
  | O.Html (_tagname, _attrs, _body) -> failwith "HTML not supported"
  | O.Html_block (_tagname, _attrs, _body) -> failwith "HTML not supported"
  | O.Html_comment _s -> failwith "HTML not supported"
  | O.NL -> ForcedNL
  | O.Paragraph l -> Paragraphe (omd_to_md_list l)
  | O.Text s -> Texte s
  | O.Emph l -> Gras (omd_to_md_list l)
  | O.Bold l -> TresGras (omd_to_md_list l)
  | O.Code_block (_lang, s) -> ParagCode s
  | O.Code (_lang, s) -> Code s
  | O.Hr -> HLigne
  | O.H (i, md) -> Titre (i,omd_to_md_list md)
  | O.Url (href, l, _title) -> Lien (omd_to_md_list l,href)
  | O.Ul l -> List (List.map omd_to_md_list l)
  | O.Ulp l -> List (List.map omd_to_md_list l)
  | O.Ol l -> Enum (List.map omd_to_md_list l)
  | O.Olp l -> Enum (List.map omd_to_md_list l)


(* for debug only, this deals with Omd.md type *)
let _print_omd md =
  List.iter
  (fun e -> Format.printf "%s" (Omd.to_markdown e))
  md

(** Affiche #### de longueur i *)
let rec printHi fmt i =
  if i = 0 then Format.fprintf fmt ""
  else Format.fprintf fmt "#%a" printHi (i-1)

(** Affichage de l'arbre sous forme parenthésé; Utile pour débuguer. *)
let rec valueprint_list fmt l =
  List.iter (fun x -> Format.fprintf fmt "%a@?" valueprint x) l
and valueprint fmt e =
  let module F = Format in
  match e with
  | Texte s -> F.fprintf fmt "Texte(%s)" s
  | Titre (i,l) -> F.fprintf fmt "H%d(%a)" i valueprint_list l
  | HLigne -> F.fprintf fmt "Hline"
  | Gras l -> F.fprintf fmt "Gras(%a)" valueprint_list l
  | TresGras l -> F.fprintf fmt "TresGras(%a)" valueprint_list l
  | Code s -> F.fprintf fmt "Code(%s)" s
  | ParagCode s -> F.fprintf fmt "ParagCode(%s)" s
  | Lien (l,s) -> F.fprintf fmt "Lien(%a)(%s)" valueprint_list l s
  | Paragraphe l -> F.fprintf fmt "Paragraphe(%a)" valueprint_list l
  | ForcedNL -> F.fprintf fmt "NL"
  | List l -> F.fprintf fmt "List(%a)" (Pp.print_list Pp.newline valueprint_list) l
  | Enum l -> F.fprintf fmt "Enum(%a)" (Pp.print_list Pp.newline valueprint_list) l

let affiche_debug md =
  Format.printf "%a" valueprint md
let affiche_list_debug md =
  Format.printf "%a" valueprint_list md

(** Affichage de l'arbre au format markdown. *)
let rec print_list_list fmt l =
  List.iter (fun x -> Format.fprintf fmt "- @[%a@]@." print_list x) l
and print_list_enum fmt l =
  List.iteri (fun i x -> Format.fprintf fmt "%d. @[%a@]@." i print_list x) l
and print_list fmt l =
  List.iter (fun x -> Format.fprintf fmt "%a" print x) l
and print fmt e =
  let module F = Format in
  match e with
  | Texte s -> F.fprintf fmt "%s" s
  | Titre (i,l) -> F.fprintf fmt "%a%a@." printHi i print_list l
  | HLigne -> F.fprintf fmt "---"
  | Gras l -> F.fprintf fmt "*%a*" print_list l
  | TresGras l -> F.fprintf fmt "**%a**" print_list l
  | Code s -> F.fprintf fmt "`%s`" s
  | ParagCode s -> F.fprintf fmt "```@.%s@.```" s
  | Lien (l,s) -> F.fprintf fmt "[%a](%s)" print_list l s
  | Paragraphe l -> F.fprintf fmt "@.@.%a@.@." print_list l
  | ForcedNL -> F.fprintf fmt "  <RET>@."
  | List l -> F.fprintf fmt "@.%a" print_list_list l
  | Enum l ->  F.fprintf fmt "@.i%a" print_list_enum l

let affiche md =
  Format.printf "%a" print md
let affiche_list md =
  Format.printf "%a" print_list md

let to_string t =
  Format.fprintf Format.str_formatter "%a" print t;
  Format.flush_str_formatter()


let slurp ch =
  if ch <> stdin then
    let size = in_channel_length ch in
    let buf = Bytes.create size in
    really_input ch buf 0 size;
    Bytes.to_string buf
  else
    let ls = ref [] in
    try
      while true do
        ls := (input_line ch) :: !ls
      done;
      assert false
    with End_of_file ->
         let s = String.concat "\n" !ls in
         let buf = Bytes.of_string s in
         Bytes.to_string buf

let lit_channel ch =
  let mdTriche = Omd.of_string (slurp ch) in
  omd_to_md_list mdTriche

let lit_fichier f =
  let ch = open_in f in
   lit_channel ch

let lit_string s =
  let mdTriche = Omd.of_string s in
  omd_to_md_list mdTriche
