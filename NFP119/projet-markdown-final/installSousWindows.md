
Les information contenues dans ce fichier sont tirées de:

https://web.archive.org/web/20170827013714/http://themargin.io/2017/02/02/OCaml_on_win/

# Pré-requis: Windows Subsystem for Linux (bash shell sous windows)
Cette procédure suppose que vous avez un windows récent avec bash (WSL
ci-dessous signifie cela). Sous windows 10 vous pouvez l'installer en
suivant la procédure ici:

https://docs.microsoft.com/en-us/windows/wsl/install-win10

Les commandes ci-dessous doivent être exécutées dans une fenêtre de
shell bash.

Si vous avez un windows plus ancien la solution standard est
d'installer cygwin 

# Installer opam et Ocaml

```
sudo add-apt-repository ppa:avsm/ppa
sudo apt-get update
sudo apt-get install ocaml opam m4
```

# Installer dune

```
opam install dune
```

# Modifier le path sous bash
Afin que le chemin vers ocaml et dune soit accessible, il faut ajouter
`eval "$(opam config env)"` à la fin du fichier .bashrc à la racine de
votre répertoire personnel bash. Vous pouvez faire cela en éditant le
fichier (~/.bashrc) ou bien en exécutant (une seule fois) la commande
ci-dessous

```
touch ~/.bashrc
eval "$(opam config env)" >> ~/.bashrc
```
