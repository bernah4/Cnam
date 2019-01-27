#include <stdio.h>
#include <signal.h>
int pip[2];
onintr()
{
    close(pip[1]);
    printf("fin wesh?\n");
    return (0);
}
main()
{
    extern onintr();
    int nb_ecrit;
    int pid;
    /*  ouverture d'un pipe */
    if(pipe(pip))
    {
        perror("pipe");
        exit(1);
    }
    pid = fork();
    if (pid == 0)
    {
        close(pip[0]);
        close(pip[1]);
        printf("Je suis le fils\n");
        exit(0);
    }
    else
    {
        close(pip[0]);
        signal(SIGPIPE,onintr());
        for(;;){
            if ((nb_ecrit = write(pip[1], "ABC", 3)) == -1)
            {
                perror ("pb write");
                exit(0);
            }
            else
                printf ("retour du write : %d\n", nb_ecrit);
        }
    }
    return (0);
}
