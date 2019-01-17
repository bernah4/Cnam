(* **************************************************************** *)
(* ************* PAS BESOIN DE TOUCHER À CE FICHIER ************* *)


let input_file:string option ref = ref None
let set_input_file s = (input_file := Some s)
let output_file:string option ref = ref None
let set_output_file s = (output_file := Some s)

(* Les paramètres acceptés par le programme, en plus de -help, --help et du
   nom du fichier markdown. *)
let args = 
  [ ("-o", Arg.String set_output_file, "output.html  nom du fichier html généré.")
  ];;
(** Le message en cas de lancement erroné du programme ou avec l'option -help *)
let usage = "navig [ input.md ] [-o output.html]

Traduit le fichier input.md ou l'entrée standard.

Écrit la traduction dans fichier.html (par défaut: sortie standard).
"
let show_help() = Arg.usage args usage

(** FONCTION PRINCIPALE, lancée au démarrage du porgramme

- lit le nom du fichier passé en paramètre
- parse ce fichier au format markdown
- lance la fonction de traduction (qui doit être complétées par les étudiants
- écrit l'HTML dans le fichier du même nom mais avec l'extention ".html". *)
let main () =
  let _ = 
    try Arg.parse args set_input_file usage
    with _ -> (print_string "Ce fichier n'existe pas.\n"; show_help();exit 1) in    
  (* CHOIX DU FICHIER D'ENTRÉE *)
  let inchannel =
    match !input_file with
      None -> stdin
    | Some f ->
       if not (Sys.file_exists f) then begin
           Printf.eprintf "File %s does not exist.\n" f;
           exit 2;
         end;
       open_in f in
  (* CHOIX DU FICHIER DE SORTIE *)
  let outfmt = match !output_file with
      None -> Format.std_formatter
    | Some f -> let oc = open_out f in
                Format.formatter_of_out_channel oc in
  (* LECTURE DU MARKDOWN *)
  let md:Md.tree list = Md.lit_channel inchannel in
  (* CALCUL DE LA TRADUCTION *)
  let traduc = Traduc.build_html_page_body md in
  (* ÉCRITURE DE LA TRADUCTION *)
  Format.fprintf outfmt "%a" Html.print_htmlpage traduc
;;
(* Lancement de la fonction principale *)
main()
