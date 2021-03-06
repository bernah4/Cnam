open Utils
open Printf

(** references, instances created in [Parser.main_parse] and
    accessed in the [Backend] module. *)
module R = Map.Make(String)
class ref_container : object
  val mutable c : (string * string) R.t
  method add_ref : R.key -> string -> string -> unit
  method get_ref : R.key -> (string * string) option
  method get_all : (string * (string * string)) list
end = object
  val mutable c = R.empty
  val mutable c2 = R.empty

  method get_all = R.bindings c

  method add_ref name title url =
    c <- R.add name (url, title) c;
    let ln = String.lowercase name in (* lowercase_ascii dans les nelles version d'ocaml *)
    if ln <> name then c2 <- R.add ln (url, title) c2

  method get_ref name =
    try
      let r =
        try
          R.find name c
        with Not_found ->
          let ln = String.lowercase name in (* lowercase_ascii dans les nelles version d'ocaml *)
          try
            R.find ln c
          with Not_found ->
            R.find ln c2
      in
      Some r
    with Not_found ->
      None
end

type element =
  | H of int * t
  | Paragraph of t
  | Text of string
  | Emph of t
  | Bold of t
  | Ul of t list
  | Ol of t list
  | Ulp of t list
  | Olp of t list
  | Code of name * string
  | Code_block of name * string
  | Br
  | Hr
  | NL
  | Url of href * t * title
  | Ref of ref_container * name * t * fallback
  | Img_ref of ref_container * name * alt * fallback
  | Html of name * (string * string option) list * t
  | Html_block of name * (string * string option) list * t
  | Html_comment of string
  | Raw of string
  | Raw_block of string
  | Blockquote of t
  | Img of alt * src * title
  | X of
      < name : string;
        to_html : ?indent:int -> (t -> string) -> t -> string option;
        to_sexpr : (t -> string) -> t -> string option;
        to_t : t -> t option >
and fallback = < to_string : string ; to_t : t >
and name = string
and alt = string
and src = string
and href = string
and title = string
and t = element list


let rec loose_compare t1 t2 = match t1,t2 with
  | H (i, e1)::tl1, H (j, e2)::tl2 when i = j ->
      (match loose_compare e1 e2 with
       | 0 -> loose_compare tl1 tl2
       | i -> i)
  | Emph e1::tl1, Emph e2::tl2
  | Bold e1::tl1, Bold e2::tl2
  | Blockquote e1::tl1, Blockquote e2::tl2
  | Paragraph e1::tl1, Paragraph e2::tl2
    ->
      (match loose_compare e1 e2 with
       | 0 -> loose_compare tl1 tl2
       | i -> i)

  | Ul e1::tl1, Ul e2::tl2
  | Ol e1::tl1, Ol e2::tl2
  | Ulp e1::tl1, Ulp e2::tl2
  | Olp e1::tl1, Olp e2::tl2
    ->
      (match loose_compare_lists e1 e2 with
       | 0 -> loose_compare tl1 tl2
       | i -> i)

  | (Code _ as e1)::tl1, (Code _ as e2)::tl2
  | (Br as e1)::tl1, (Br as e2)::tl2
  | (Hr as e1)::tl1, (Hr as e2)::tl2
  | (NL as e1)::tl1, (NL as e2)::tl2
  | (Html _ as e1)::tl1, (Html _ as e2)::tl2
  | (Html_block _ as e1)::tl1, (Html_block _ as e2)::tl2
  | (Raw _ as e1)::tl1, (Raw _ as e2)::tl2
  | (Raw_block _ as e1)::tl1, (Raw_block _ as e2)::tl2
  | (Html_comment _ as e1)::tl1, (Html_comment _ as e2)::tl2
  | (Img _ as e1)::tl1, (Img _ as e2)::tl2
  | (Text _ as e1)::tl1, (Text _ as e2)::tl2
    ->
      (match compare e1 e2 with
       | 0 -> loose_compare tl1 tl2
       | i -> i)

  | Code_block(l1,c1)::tl1, Code_block(l2,c2)::tl2
    ->
      (match compare l1 l2, String.length c1 - String.length c2 with
       | 0, 0 ->
           (match compare c1 c2 with
            | 0 -> loose_compare tl1 tl2
            | i -> i)
       | 0, 1 ->
           (match compare c1 (c2^"\n") with
            | 0 -> loose_compare tl1 tl2
            | i -> i)
       | 0, -1 ->
           (match compare (c1^"\n") c2 with
            | 0 -> loose_compare tl1 tl2
            | i -> i)
       | i, _ -> i
      )

  | Url (href1, t1, title1)::tl1, Url (href2, t2, title2)::tl2
    ->
      (match compare href1 href2 with
       | 0 -> (match loose_compare t1 t2 with
           | 0 -> (match compare title1 title2 with
               | 0 -> loose_compare tl1 tl2
               | i -> i)
           | i -> i)
       | i -> i)

  | Ref (ref_container1, name1, x1, fallback1)::tl1,
    Ref (ref_container2, name2, x2, fallback2)::tl2
    ->
      (match compare name1 name2, loose_compare x1 x2 with
       | 0, 0 ->
           let cff =
             if fallback1#to_string = fallback2#to_string then
               0
             else
               loose_compare (fallback1#to_t) (fallback2#to_t)
           in
           if cff = 0 then
             match
               compare (ref_container1#get_all) (ref_container2#get_all)
             with
             | 0 -> loose_compare tl1 tl2
             | i -> i
           else
             cff
       |  0, 1 | 1,  0 ->  1
       | -1, 0 | 0, -1 -> -1
       | _ -> 1)

  | Img_ref (ref_container1, name1, x1, fallback1)::tl1,
    Img_ref (ref_container2, name2, x2, fallback2)::tl2
    ->
      (match compare (name1, x1) (name2, x2) with
       | 0 ->
           let cff =
             if fallback1#to_string = fallback2#to_string then
               0
             else
               loose_compare (fallback1#to_t) (fallback2#to_t)
           in
           if cff = 0 then
             match
               compare (ref_container1#get_all) (ref_container2#get_all)
             with
             | 0 -> loose_compare tl1 tl2
             | i -> i
           else
             cff
       | i -> i)

  | X e1::tl1, X e2::tl2 ->
      (match compare (e1#name) (e2#name) with
       | 0 -> (match compare (e1#to_t) (e2#to_t) with
           | 0 -> loose_compare tl1 tl2
           | i -> i)
       | i -> i)
  | X _::_, _ -> 1
  | _, X _::_ -> -1
  | _ -> compare t1 t2

and loose_compare_lists l1 l2 =
  match l1, l2 with
  | [], [] -> 0
  | e1::tl1, e2::tl2 ->
      (match loose_compare e1 e2 with
       | 0 -> loose_compare_lists tl1 tl2
       | i -> i)
  | _, [] -> 1
  | _ -> -1

type delim =
  | Ampersand
  | At
  | Backquote
  | Backslash
  | Bar
  | Caret
  | Cbrace
  | Colon
  | Comma
  | Cparenthesis
  | Cbracket
  | Dollar
  | Dot
  | Doublequote
  | Exclamation
  | Equal
  | Greaterthan
  | Hash
  | Lessthan
  | Minus
  | Newline
  | Obrace
  | Oparenthesis
  | Obracket
  | Percent
  | Plus
  | Question
  | Quote
  | Semicolon
  | Slash
  | Space
  | Star
  | Tab
  | Tilde
  | Underscore

and tok =
  | Tag of name * extension
  | Word of string
  | Number of string
  | Delim of int * delim

and extension =
  <
    parser_extension : t -> tok list -> tok list -> (t * tok list * tok list) option;
    to_string : string;
  >

type extensions = extension list

let empty_extension =
  object
    method parser_extension _r _p _l = None
    method to_string = ""
  end

let rec normalise_md l =
  if debug then
    eprintf "(OMD) normalise_md\n%!";
  let rec loop = function
    | [NL;NL;NL;NL;NL;NL;NL;]
    | [NL;NL;NL;NL;NL;NL;]
    | [NL;NL;NL;NL;NL;]
    | [NL;NL;NL;NL;]
    | [NL;NL;NL;]
    | [NL;NL]
    | [NL] -> []
    | [] -> []
    | NL::NL::NL::tl -> loop (NL::NL::tl)
    | Text t1::Text t2::tl -> loop (Text(t1^t2)::tl)
    | NL::(((Paragraph _|H _|Code_block _|Ol _|Ul _|Olp _|Ulp _)::_) as tl) -> loop tl
    | Paragraph[Text " "]::tl -> loop tl
    | Paragraph[]::tl -> loop tl
    | Paragraph(p)::tl -> Paragraph(loop p)::loop tl
    | H (i, v)::tl -> H(i, loop v)::loop tl
    | Emph v::tl -> Emph(loop v)::loop tl
    | Bold v::tl -> Bold(loop v)::loop tl
    | Ul v::tl -> Ul(List.map loop v)::loop tl
    | Ol v::tl -> Ol(List.map loop v)::loop tl
    | Ulp v::tl -> Ulp(List.map loop v)::loop tl
    | Olp v::tl -> Olp(List.map loop v)::loop tl
    | Blockquote v::tl -> Blockquote(loop v)::loop tl
    | Url(href,v,title)::tl -> Url(href,(loop v),title)::loop tl
    | Text _
    | Code _
    | Code_block _
    | Br
    | Hr
    | NL
    | Ref _
    | Img_ref _
    | Html _
    | Html_block _
    | Html_comment _
    | Raw _
    | Raw_block _
    | Img _
    | X _ as v::tl -> v::loop tl
  in
  let a = loop l in
  let b = loop a in
  if a = b then
    a
  else
    normalise_md b

let rec visit f = function
  | [] -> []
  | Paragraph v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Paragraph(visit f v)::visit f tl
      end
  | H (i, v) as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> H(i, visit f v)::visit f tl
      end
  | Emph v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Emph(visit f v)::visit f tl
      end
  | Bold v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Bold(visit f v)::visit f tl
      end
  | Ul v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Ul(List.map (visit f) v)::visit f tl
      end
  | Ol v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Ol(List.map (visit f) v)::visit f tl
      end
  | Ulp v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Ulp(List.map (visit f) v)::visit f tl
      end
  | Olp v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Olp(List.map (visit f) v)::visit f tl
      end
  | Blockquote v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Blockquote(visit f v)::visit f tl
      end
  | Url(href,v,title) as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Url(href,visit f v,title)::visit f tl
      end
  | Text _v as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Code _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Code_block _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Ref _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Img_ref _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Html _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Html_block _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Html_comment _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Raw _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Raw_block _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Img  _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | X  _ as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> e::visit f tl
      end
  | Br as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Br::visit f tl
      end
  | Hr as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> Hr::visit f tl
      end
  | NL as e::tl ->
      begin match f e with
      | Some(l) -> l@visit f tl
      | None -> NL::visit f tl
      end
