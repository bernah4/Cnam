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

(* Affiche sur le terminal au format parenthésé *)
val affiche_debug: tree -> unit
val affiche_list_debug: tree list -> unit

(* Affiche sur le terminal au format HTML *)
val affiche_list : tree list -> unit
val affiche : tree -> unit

(* création d'une chaine de caractère au format HTML *)
val to_string : tree -> string


(* formattage (ne pas utiliser) *)
val valueprint: Format.formatter -> tree -> unit
val valueprint_list: Format.formatter -> tree list -> unit

(* formattage (ne pas utiliser) *)
val print_list : Format.formatter -> tree list -> unit
val print : Format.formatter -> tree -> unit


val print_htmlpage: Format.formatter -> tree -> unit
