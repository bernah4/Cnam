set -o xtrace
ocamlc.opt -c -I omd -o html.cmi html.mli
ocamlc.opt -c -I omd -o md.cmi md.mli
ocamlc.opt -c -I omd -o traduc.cmi traduc.mli
ocamlc.opt -c -I omd -o md_to_html.cmo md_to_html.ml
ocamlc.opt -c -I omd -o pp.cmo pp.ml
ocamlc.opt -c -I omd -o omd\representation.cmi omd\representation.mli
ocamlc.opt -c -I omd -o omd\utils.cmi omd\utils.mli
ocamlc.opt -c -I omd -o omd\backend.cmi omd\backend.mli
ocamlc.opt -c -I omd -o omd\lexer.cmi omd\lexer.mli
ocamlc.opt -c -I omd -o omd\parser.cmi omd\parser.mli
ocamlc.opt -c -I omd -o omd\omd.cmi omd\omd.mli
ocamlc.opt -c -I omd -o html.cmo html.ml
ocamlc.opt -c -I omd -o md.cmo md.ml
ocamlc.opt -c -I omd -o traduc.cmo traduc.ml
ocamlc.opt -c -I omd -o omd\omd.cmo omd\omd.ml
ocamlc.opt -c -I omd -o omd\backend.cmo omd\backend.ml
ocamlc.opt -c -I omd -o omd\lexer.cmo omd\lexer.ml
ocamlc.opt -c -I omd -o omd\parser.cmo omd\parser.ml
ocamlc.opt -c -I omd -o omd\representation.cmo omd\representation.ml
ocamlc.opt -c -I omd -o omd\utils.cmo omd\utils.ml
ocamlc.opt bigarray.cma -I omd pp.cmo html.cmo omd\utils.cmo omd\representation.cmo omd\backend.cmo omd\lexer.cmo omd\parser.cmo omd\omd.cmo md.cmo traduc.cmo md_to_html.cmo -o md_to_html.byte
