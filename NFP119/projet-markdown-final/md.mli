
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

(** Lit le canal d'entrée au format markdown et retourne l'arbre
    correspondant *)
val lit_channel : in_channel -> tree list

(** Lit le fichier au format markdown et retourne l'arbre
    correspondant *)
val lit_fichier : string -> tree list

(** Lit la chaine au format markdown et retourne l'arbre
    correspondant *)
val lit_string : string -> tree list


(* Affiche sur le terminal au format parenthésé *)
val affiche_debug: tree -> unit
val affiche_list_debug: tree list -> unit

(* Affiche sur le terminal au format HTML *)
val affiche_list : tree list -> unit
val affiche : tree -> unit


(* Formattage des arbres au format parenthésé *)
val valueprint : Format.formatter -> tree -> unit
val valueprint_list : Format.formatter -> tree list -> unit

(* Formattage au format markdown *)
val print_list : Format.formatter -> tree list -> unit
val print : Format.formatter -> tree -> unit

(* création d'une chaine de caractère au format HTML *)
val to_string : tree -> string

