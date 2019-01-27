#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define CLE 316

struct requete {
    long letype; 
    int nb1;
    int nb2;
    int pid;
};
struct reponse {
    long letype; 
    int res;
};

int     main()
{
    int msqid,l;
    struct requete la_requete;
    struct reponse la_reponse;

    /*   allocation  MSQ */
    msqid =  msgget(CLE, IPC_CREAT | IPC_EXCL | 0666);

    /* lecture d'une requ?te */
    msgrcv(msqid,&la_requete,sizeof(struct requete)-4,1,0);

    /* envoi de la r?ponse  */
    la_reponse.res = la_requete.nb1 + la_requete.nb2;
    la_reponse.letype = la_requete.pid; 
    printf("nb1 = %d et nb2 = %d \n", la_requete.nb1, la_requete.nb2);
    
    msgsnd(msqid,&la_reponse,sizeof(struct reponse)-4,0);
    exit(0);
    return (0);
}

