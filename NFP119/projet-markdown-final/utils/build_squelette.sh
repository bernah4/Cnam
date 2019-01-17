BINNAME=markdown

echo Nettoyage
make clean
mkdir -p projet-$BINNAME
rm -rf projet-$BINNAME/*
echo

function cpdir() {
    mkdir -p projet-$BINNAME/$1
    find $1 -maxdepth 1 \( -name "*.ml" -or -name "*.mli" -or -name "*.sh" -or -name "*.md" -or -name "*.bat" -or -name "_tags" -or -name "Makefile" -or -name ".merlin" \) -exec ../../../../utils/removesol/removesol {} -output projet-$BINNAME/{} \;
    find projet-$BINNAME/$1 -maxdepth 1 -name "*.sh" -exec chmod u+x {} \;
}
# On peut pas faire -exec car j'attrape des fichiers que je suis en train de créer...

echo copie des fichiers  et suppression des solutions
cpdir .
cpdir omd
cpdir utils
cpdir tests

pushd projet-$BINNAME ; compile.sh
make clean
popd
# foo:	for f in *. ; do ../../../../utils/removesol/removesol -input $$f -output projet-$(BINNAME)/$$f ; done
# @echo
# @echo Copie du makefile
# ../../../../utils/removesol/removesol -input Makefile -output projet-$(BINNAME)/Makefile
# cp _tags projet-$(BINNAME)/
# cp doc.odocl projet-$(BINNAME)/
# cp compile.bat compile.sh projet-$(BINNAME)/
# @echo
# @echo importation des tests...
# rm -rf projet-$(BINNAME)/tests
# cp -ra tests projet-$(BINNAME)/
# @echo
# @echo nettoyage et fabrication de la doc
# @echo
# touch projet-$(BINNAME)/.depend
# rm -rf projet-$(BINNAME)/docsrc	
# mkdir projet-$(BINNAME)/docsrc
# cp docsrc/graphics.mli projet-$(BINNAME)/docsrc/
# cd projet-$(BINNAME) ; make
# cd projet-$(BINNAME) ; make doc
# cd projet-$(BINNAME) ; make clean
# cd ..
# tar cvfz projet-$(BINNAME).tgz projet-$(BINNAME)
# zip -r projet-$(BINNAME) projet-$(BINNAME)
# @echo FINI
