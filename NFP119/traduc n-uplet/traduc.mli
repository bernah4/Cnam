(** La fonction qui construit tout le <body> de la page, y compris la
    table des matière si implanté *)
val build_html_page_body: Md.tree list -> Html.tree

val traduit: (int*Html.tree list*Md.tree) -> int*Html.tree list*Html.tree 
val traduit_list: (int* Html.tree list* Md.tree list) -> int*Html.tree list*Html.tree  list   

