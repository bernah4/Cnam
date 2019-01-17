(** Ce module contient la fonction de traduction markdown -> html. À
 implanter par les étudiants *)

let rec traduit ((nb:int),(ttl:Html.tree list),(e:Md.tree)): int *Html.tree list * Html.tree =
    match e with
    | Texte u -> (nb, ttl,  Word u)
    | Titre (1, l) ->let (nbr, ttlr, ll) = traduit_list ((nb+1), ttl, l) in 
        (nbr, ttl @ [A ([Href ("#titre"^string_of_int nb)], [Li([],ll)])], H1([Id ("titre"^string_of_int nb)], ll))
    | Titre (2, l) ->let (nbr, ttlr, ll) = traduit_list ((nb+1), ttl, l) in 
        (nbr, ttl @ [A ([Href ("#titre"^string_of_int nb)], [Li([],ll)])], H2([Id ("titre"^string_of_int nb)], ll))
    | Titre (3, l) ->let (nbr, ttlr, ll) = traduit_list ((nb+1), ttl, l) in 
        (nbr, ttl @ [A ([Href ("#titre"^string_of_int nb)], [Li([], ll)])], H3([Id ("titre"^string_of_int nb)], ll))
    | Titre (4, l) ->let (nbr, ttlr, ll) = traduit_list ((nb+1), ttl, l) in 
        (nbr, ttl @ [A ([Href ("#titre"^string_of_int nb)], [Li([], ll)])], H4([Id ("titre"^string_of_int nb)], ll))
    | Titre (5, l) ->let (nbr, ttlr, ll) = traduit_list ((nb+1), ttl, l) in 
        (nbr, ttl @ [A ([Href ("#titre"^string_of_int nb)], [Li([], ll)])], H5([Id ("titre"^string_of_int nb)], ll))
    | Gras l -> let (nbr, ttlr, ll) = traduit_list (nb, ttl, l) in
                    (nbr, ttlr, I ([],ll))
    | TresGras l -> let (nbr, ttlr, ll) = traduit_list (nb, ttl, l) in
                    (nbr, ttlr, B ([],ll))
    | Paragraphe l -> let (nbr, ttlr, ll) = traduit_list (nb, ttl, l) in
                    (nbr, ttlr, P ([],ll))
    | HLigne  -> (nb, ttl, Hrule)
    | ForcedNL -> (nb , ttl,  Br)
    | Code s -> (nb, ttl, Code ([], s))
    | ParagCode s -> (nb, ttl, Pre ([],[Code([],s)]))
    | Lien (l, url) -> let (nbr, ttlr, ll) = traduit_list (nb, ttl, l) in
                    (nbr, ttlr, A([Href url],ll))
    | List l -> let (nbr, ttlr, ll) = traduit_list_list nb ttl l in
                    (nbr, ttlr, Ol ([], ll))
    | Enum l -> let (nbr, ttlr, ll) = traduit_list_list nb ttl l in
                    (nbr, ttlr, Ul ([], ll))
and traduit_list ((nb:int),(ttl:Html.tree list),(l: Md.tree list)): int*Html.tree list*Html.tree list =
    match l with
    | [] -> (nb, ttl,[])
    | a :: r -> let (nbr,ttlr, z) =traduit (nb, ttl, a) in
                    let (nbrr, ttlrr, rr) = traduit_list (nbr, ttlr, r) in
                        (nbrr,ttlrr, z::rr)
and traduit_list_list (nb:int)(ttl:Html.tree list)(l: Md.tree list list): int*Html.tree list*Html.tree list =
    match l with
    |[] -> (nb, ttl, [])
    | a :: r -> let (nbr, ttlr, ll) = traduit_list (nb, ttl, a) in
                    let (nbrr, ttlrr, rr) =  traduit_list_list nbr ttlr r in
                    (nbrr, ttlrr, Li([], ll)::rr)


(** Lance la traduction et englobe le tout dans la balise <body>,
 ajoutera éventuellement la table des matières *)
let build_html_page_body md =
    let (nb, titrelist, reshtml) = traduit_list (0, [], md) in
    Html.Body ([], [Html.Ul([], titrelist)]@reshtml)
