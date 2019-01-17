(** La fonction qui construit tout le <body> de la page, y compris la
    table des matière si implanté *)
type 'a section  = {num : int; tabmat : Html.tree list; abr : 'a}
val build_html_page_body: Md.tree list -> Html.tree

val traduit: Md.tree section -> Html.tree section
val traduit_list: Md.tree list section -> Html.tree list section   

