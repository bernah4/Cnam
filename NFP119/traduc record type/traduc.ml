(** Ce module contient la fonction de traduction markdown -> html. À
 implanter par les étudiants *)

type 'a section = {num : int ; tabmat : Html.tree list; abr : 'a}

let rec traduit (e:Md.tree section): Html.tree section =
    match e.abr with
    | Texte u -> {e with abr = Word u}
    | Titre (1, l) ->let ret = traduit_list {e with abr = l} in 
        {num = ret.num + 1; tabmat = ret.tabmat @ [Li([],[A ([Href ("#titre"^string_of_int e.num)], ret.abr)])]; abr = H1([Id ("titre"^string_of_int e.num)], ret.abr)}
    | Titre (2, l) ->let ret = traduit_list {e with abr = l} in 
        {num = ret.num + 1; tabmat = ret.tabmat @ [Li([],[A ([Href ("#titre"^string_of_int e.num)], ret.abr)])]; abr = H2([Id ("titre"^string_of_int e.num)], ret.abr)}
    | Titre (3, l) ->let ret = traduit_list {e with abr = l} in 
        {num = ret.num + 1; tabmat = ret.tabmat @ [Li([],[A ([Href ("#titre"^string_of_int e.num)], ret.abr)])]; abr = H3([Id ("titre"^string_of_int e.num)], ret.abr)}
    | Titre (4, l) ->let ret = traduit_list {e with abr = l} in 
        {num = ret.num + 1; tabmat = ret.tabmat @ [Li([],[A ([Href ("#titre"^string_of_int e.num)], ret.abr)])]; abr = H4([Id ("titre"^string_of_int e.num)], ret.abr)}
    | Titre (5, l) ->let ret = traduit_list {e with abr = l} in 
        {num = ret.num + 1; tabmat = ret.tabmat @ [Li([],[A ([Href ("#titre"^string_of_int e.num)], ret.abr)])]; abr = H5([Id ("titre"^string_of_int e.num)], ret.abr)}
    | Gras l -> let ret = traduit_list {e with abr = l} in
                    {ret with abr = I ([],ret.abr)}
    | TresGras l -> let ret = traduit_list {e with abr = l} in
                    {ret with abr = B ([],ret.abr)}
    | Paragraphe l -> let ret = traduit_list {e with abr = l} in
                    {ret with abr = P ([],ret.abr)}
    | HLigne  -> {e with abr = Hrule}
    | ForcedNL -> {e with abr = Br}
    | Code s -> {e with abr = Code ([], s)}
    | ParagCode s -> {e with abr = Pre ([],[Code([],s)])}
    | Lien (l, url) -> let ret = traduit_list {e with abr = l} in
                    {ret with abr = A([Href url],ret.abr)}
    | List l -> let ret = traduit_list_list {e with abr = l} in
                    {ret with abr = Ol ([], ret.abr)}
    | Enum l -> let ret = traduit_list_list {e with abr = l} in
                    {ret with abr = Ul ([], ret.abr)}
and traduit_list (l: Md.tree list section): Html.tree list section =
    match l.abr with
    | [] -> {l with abr = []}
    | a :: z -> let ret = traduit {l with abr = a} in
                    let retret = traduit_list {ret with abr = z} in
                        {retret with abr = ret.abr::retret.abr}
and traduit_list_list (l: Md.tree list list section): Html.tree list section=
    match l.abr with
    | [] -> {l with abr = []}
    | a :: z -> let ret = traduit_list {l with abr = a} in
                    let retret =  traduit_list_list {ret with abr = z} in
                    {retret with abr = Li([], ret.abr)::retret.abr}


(** Lance la traduction et englobe le tout dans la balise <body>,
 ajoutera éventuellement la table des matières *)
let build_html_page_body md =
    let ret = traduit_list {num = 0;tabmat =  [];abr =  md} in
    Html.Body ([], [Html.Ul([], ret.tabmat)]@ret.abr)
