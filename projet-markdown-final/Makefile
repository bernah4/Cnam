BINNAME=markdown

# le script build_squelette.sh est écrit en bash.
SHELL=bash


.PHONY: compile.sh compile.bat clean toplevel default projet-$(BINNAME).tgz

default:
	@echo "use ./compile.sh to compile"
	@echo "or ./ocamlbuild md_to_html.byte"
	@echo "or ./ocamlbuild md_to_html.native"

compile.sh:
	utils/gen_compile.sh

compile.bat: clean
	ocamlbuild -I . -I omd traduc.byte -classic-display | grep -v ocamldep | sed -e "s/.*\(ocamlc.opt.*\)/\1/" | sed -e "s/\//\\\\/g"  > compile.bat
	chmod a+x compile.bat


clean: 
	rm -f *.cmi *.cmo
	rm -f omd/*.cmi omd/*.cmo
	rm -f *.byte *.native *.exe *.bc
	rm -f omd/*.byte omd/*.native omd/*.exe omd/*.bc
	rm -rf _build
	rm -rf omd/_build


toplevel:
	ocaml -I . -I omd -init utils/init.ml

# utiliser ocamlbuild, il faut faire make clean d'abord si on a
# utilisé compile.sh avant
build:
	ocamlbuild -I . -I omd md_to_html.byte

buildnative:
	ocamlbuild -I . -I omd md_to_html.native

# Si on a utilisé dune pour compiler, le toplevel se lance comme ceci:
# ocaml -I . -I _build/default/.md_to_html.eobjs/ -I _build/default/omd/.omd.objs/ -I _build/default/omd -init utils/init.ml

