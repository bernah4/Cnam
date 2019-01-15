
open Format

exception Pas_encore_implante

type attribut =
    | Href of string
    | Color of string
    | Size of int
    | Value of int
    | Name of string
    | Id of string


type tree =
  | Empty
  | Html of attribut list * tree list
  | Head of attribut list * tree list
  | Body of attribut list * tree list
  | P of attribut list * tree list
  | B of attribut list * tree list
  | I of attribut list * tree list
  | U of attribut list * tree list
  | Font of attribut list * tree list
  | A of attribut list * tree list
  | H1 of attribut list * tree list
  | H2 of attribut list * tree list
  | H3 of attribut list * tree list
  | H4 of attribut list * tree list
  | H5 of attribut list * tree list
  | Center of attribut list * tree list
  | Title of attribut list * tree list
  | Hrule
  | Pre of attribut list * tree list (* preformatted *)
  | Code of attribut list * string (* code sample, use inside pre to avoid line breaks *)
  | Ul of  attribut list * tree list
  | Ol of  attribut list * tree list
  | Li of  attribut list * tree list
  | Word of string
  | Br

(* Les blancs: blanc, tab ou retour à la ligne. *)
let spaces = [ ' ' ;'\n'; '\t' ]

(* Les lettre accentuées en utf8 et leur traduction en iso-latin. Les
   librairie String et Graphics ne supporte pas correctement
   l'utf-8. *)
let _utf_8_codes = [
    '\xc3', ['\xa9','\xe9' ; (* é *)
             '\xa8','\xe8'; (* è *)
             '\xaa','\xea'; (* ê *)
             '\xab','\xeb'; (* ë *)
             '\xa0','\xe0'; (* à *)
             '\xa2','\xe2'; (* â *)
             '\xae','\xee'; (* î *)
             '\xaf','\xef'; (* ï *)
             '\xb4','\xf4'; (* ô *)
             '\xb9','\xf9'; (* ù *)
             '\xbc','\xfc'; (* ü *)
            ]
  ]


(* Detecte les pattern de lettres accentuées utf8 et les transforme en
   caractère iso-latin, Graphics ne gère pas l'utf8. *)
let _code table s =
  let res = ref "" in
  let i = ref 0 in
  while !i < String.length s do
    let c = s.[!i] in
    try
      let lrepl = List.assoc c table in
      let d = s.[!i+1] in
      let repl = List.assoc d lrepl in
      res := !res^String.make 1 repl;
      i := !i + 2
    with Not_found -> (res := !res^String.make 1 s.[!i]; i := !i + 1)
  done;
  !res


let space fmt () = fprintf fmt "@ "
let semi fmt () = fprintf fmt ";@ "

let rec print_list sep print fmt = function
  | [] -> ()
  | [x] -> print fmt x
  | x :: r -> print fmt x; sep fmt (); print_list sep print fmt r

let p_attribute fmt attr =
  match attr with
  | Href s -> fprintf fmt "href=\"%s\"" s
  | Color s -> fprintf fmt "color=\"%s\"" s
  | Size n -> fprintf fmt "size=\"%d\"" n
  | Value n -> fprintf fmt "value=\"%d\"" n
  | Name s -> fprintf fmt "name=\"%s\"" s
  | Id s -> fprintf fmt "id=\"%s\"" s

let rec valueprint fmt t =
  let pl = print_list semi valueprint in
  let pla fmt l =
    if l=[] then Format.fprintf fmt ""
    else Format.fprintf fmt "{@[<h>%a@]}," (print_list space p_attribute) l in
  match t with
    | Empty -> fprintf fmt "<empty?>"
    | Html (attr,trees) -> fprintf fmt "html(@[%a@ [ @[%a@] ]@])" pla attr pl trees
    | Head (attr,trees) -> fprintf fmt "head(@[%a@ [ @[%a@] ]@])" pla attr pl trees
    | Body (attr,trees) -> fprintf fmt "body(@[%a@ @[[ @[%a@] ]@])" pla attr pl trees
    | P (_attr,trees) -> fprintf fmt "P([ @[%a@] ])" pl trees
    | B (_attr,trees) -> fprintf fmt "B([ @[%a@] ])" pl trees
    | I (_attr,trees) ->   fprintf fmt "I([ @[%a@] ])" pl trees
    | U (_attr,trees) ->   fprintf fmt "U([ @[%a@] ])" pl trees
    | Center (attr,trees) ->   fprintf fmt "Center(%a[ @[%a@] ])" pla attr pl trees
    | A (attr,trees) -> fprintf fmt "A(%a[ @[%a@] ])" pla attr pl trees
    | Font (attr,trees) -> fprintf fmt "Font(%a[ @[%a@] ])" pla attr pl trees
    | H1 (_attr,trees) -> fprintf fmt "H1([ @[%a@] ])" pl trees
    | H2 (_attr,trees) -> fprintf fmt "H2([ @[%a@] ])" pl trees
    | H3 (_attr,trees) -> fprintf fmt "H3(@; [ @[%a@] ])" pl trees
    | H4 (_attr,trees) -> fprintf fmt "H4(@; [ @[%a@] ])" pl trees
    | H5 (_attr,trees) -> fprintf fmt "H5(@; [ @[%a@] ])" pl trees
    | Title (attr,trees) -> fprintf fmt "title(%a@; [ @[%a@] ])" pla attr pl trees
    | Word s -> fprintf fmt "Word [@[%s@]]" s
    | Hrule -> fprintf fmt "Hrule"
    | Pre(attr,trees) -> fprintf fmt "Pre(%a[ @[%a@] ])" pla attr pl trees
    | Code(attr,s) -> fprintf fmt "Code(%a[ @[%s@] ])" pla attr s
    | Ul(attr,l) -> fprintf fmt "Ul(%a[ @[%a@] ])" pla attr pl l
    | Ol(attr,l) -> fprintf fmt "Ol(%a[ @[%a@] ])" pla attr pl l
    | Li(attr,l) -> fprintf fmt "Li(%a[ @[%a@] ])" pla attr pl l
    | Br -> fprintf fmt "Br"

let valueprint_list fmt t =
  Format.fprintf fmt "%a" (print_list semi valueprint) t


let affiche_debug t =
  Format.printf "%a" valueprint t
let affiche_list_debug t =
  Format.printf "%a" valueprint_list t

(* Pour la compatibilité ocaml 4.02 *)
let split_on_char sep s =
  let r = ref [] in
  let j = ref (String.length s) in
  for i = String.length s - 1 downto 0 do
    if String.unsafe_get s i = sep then begin
      r := String.sub s (i + 1) (!j - i - 1) :: !r;
      j := i
    end
  done;
  String.sub s 0 !j :: !r

let rec print fmt t =
  let pl = print_list space print in
  let pla fmt l =
    if l=[] then Format.fprintf fmt ""
    else Format.fprintf fmt "@[<h>%a@]" (print_list space p_attribute) l in
  fprintf
    fmt "@[%a@]"
    (fun fmt t -> 
      match t with
      | Empty -> fprintf fmt ""
      | Html (attr,trees) -> fprintf fmt "<html %a>@[%a@]</html>@." pla attr pl trees
      | Head (attr,trees) -> fprintf fmt "<head %a>@[%a@]</head>@." pla attr pl trees
      | Body (attr,trees) -> fprintf fmt "<body %a>@[%a@]</body>@." pla attr pl trees
      | P (_attr,trees) -> fprintf fmt "@,@[<2><p>%a</p>@]@." pl trees
      | B (_attr,trees) -> fprintf fmt "<b>@[%a@]</b>" pl trees
      | I (_attr,trees) ->   fprintf fmt "<i>@[%a@]</i>@," pl trees
      | U (_attr,trees) ->   fprintf fmt "<u>@[%a@]</u>@," pl trees
      | Center (attr,trees) ->   fprintf fmt "<center %a>@[%a@]</center>@." pla attr pl trees
      | A (attr,trees) -> fprintf fmt "<a %a> @[%a@]</a>@," pla attr pl trees
      | Font (attr,trees) -> fprintf fmt "<font %a>@[%a@]</font>@," pla attr pl trees
      | H1 (attr,trees) -> fprintf fmt "@.@[%s %a>%a</h1>@]@." "<h1"pla attr pl trees
      | H2 (attr,trees) -> fprintf fmt "@.@[%s %a>%a@]</h2>@." "<h2" pla attr pl trees
      | H3 (attr,trees) -> fprintf fmt "@.@[%s %a>%a@]</h3>@." "<h3" pla attr pl trees
      | H4 (attr,trees) -> fprintf fmt "@.@[%s %a>%a@]</h4>@." "<h4" pla attr pl trees
      | H5 (attr,trees) -> fprintf fmt "@.@[%s %a>%a@]</h5>@." "<h5" pla attr pl trees
      | Title (attr,trees) -> fprintf fmt "@.<title %a>@[%a@]</title>@." pla attr pl trees
      | Word s -> fprintf fmt "%s" s
      | Hrule -> fprintf fmt "<hr>"
      | Pre(attr,trees) -> fprintf fmt "<pre %a>%a</pre>" pla attr pl trees
      | Code(attr,s) ->
         let ls = split_on_char '<' s in (* ocmal 4.03: String.split_on_char *)
         let news = String.concat "&lt;" ls in
         fprintf fmt "@[%s%a>%s</code>@]" "<code" pla attr news
      | Br -> fprintf fmt "<br>@."
      | Ul(attr,l) -> fprintf fmt "@[<v 2>%s %a>  %a</ul>@]" "<ul" pla attr pl l
      | Ol(attr,l) -> fprintf fmt "@[<v 2>%s %a>  %a</ol>@]" "<ol"pla attr pl l
      | Li(attr,l) -> fprintf fmt "@[<hov>%s %a>  %a</li>@]" "<li" pla attr pl l) t

let print_list fmt t =
  Format.fprintf fmt "%a" (Pp.print_list space print) t

(* Affiche sur le terminal *)
let affiche_list t =
  Format.printf "%a" print_list t
let affiche t =
  Format.printf "%a" print t


let _attribute_to_string attr =
  match attr with
  | Href s -> "href=\""^s^"\""
  | Color s -> "color=\""^s^"\""
  | Size n -> "size=\""^string_of_int n^"\""
  | Value n -> "value=\""^string_of_int n^"\""
  | Name s -> "name=\""^s^"\""
  | Id s -> "id=\""^s^"\""

let to_string t =
  fprintf str_formatter "%a" print t;
  flush_str_formatter()


(* Découpage d'une chaine en mots (séparateur: blanc, tab ou retour à
   la ligne.) *)
let rec _split i j txt =
  let res =
    if j = String.length txt
    then
      if i<j then [(String.sub txt i (j-i))] else []
    else
      if List.mem (txt.[j]) spaces
      then
        if j>i
        then
          let wrd = String.sub txt i (j-i) in
          wrd :: (_split (j+1) (j+1) txt)
        else _split (j+1) (j+1) txt
      else _split i (j+1) txt
  in
  res


let print_htmlpage fmt body =
  let header = "<html>\n<head profile=\"http://www.w3.org/2005/10/profile\">" in
  let meta = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" in
  let endhead = "</head>" in
  let endhhtml = "</head>" in
  Format.fprintf fmt "%s@.%s@.%s%a@.%s" header meta endhead
                 (Pp.print_list Pp.space print) [body] endhhtml
