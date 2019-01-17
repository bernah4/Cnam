
# Ce qu'il faut:
## ocaml
Documentation pour l'installation: https://ocaml.org/docs/install.fr.html
### Sous linux


### Sous MacOs
Sur macOS OCaml et/ou OPAM peuvent êtres installés grâce aux systèmes
de gestion de paquets tiers.
#### Homebrew
brew install ocaml
brew install opam 
#### Fink
apt install ocaml
#### MacPorts
port install ocaml
port install opam

# Sous Windows
L'installation est un peu pénible sou swindows, ceci reste la
meilleure à cema connaissance. Il y a cependant 2 typos dans cette explication
https://web.archive.org/web/20170827013714/http://themargin.io/2017/02/02/OCaml_on_win/

## dune

# Compiler:

dune build md2html.exe

# Exécuter
## 1ère solution:
dune exec ./md2html.exe
## 2e solution
./_build/default/md2html.exe




# Un toplevel pour tester:
dune build debugtoplevel.exe
ledit _build/default/debugtoplevel.exe -I _build/default/.md.objs -I _build/default/omd/.omd.objs

open Md;;
#TODO: pas de librairie pour les étudiants, sauf omd?
