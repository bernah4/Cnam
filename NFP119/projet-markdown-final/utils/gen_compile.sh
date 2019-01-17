make clean > /dev/null
if ocamlbuild -I . -I omd traduc.byte;
then
    make clean > /dev/null
    echo "set -o xtrace" > compile.sh
    ocamlbuild -I . -I omd md_to_html.byte -classic-display | grep -v ocamldep | sed -e "s/.*\(ocamlc.opt.*\)/\1/" >> compile.sh
    chmod a+x compile.sh
    make clean > /dev/null
    echo "checking compilation..."
    if ./compile.sh 2>/dev/null ;
    then echo "seems to work."
    else echo "seems to fail!"
    fi
    echo "cleaning..."
    make clean  > /dev/null
    echo "generating compile.bat..."
    cat compile.sh | sed -e "s/\//\\\\/g"  > compile.bat
    echo "Done"
else
    echo "build failed, compile.sh not generated"
fi
