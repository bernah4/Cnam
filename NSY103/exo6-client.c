#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>

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
main()
{
    int msqid, nb1, nb2; 
    struct requete la_requete;
    struct reponse la_reponse;

    /* r?cup?ration du msqid */
    msqid = msgget(CLE,0);

    /* pr?paration de la requ?te et envoi */
    printf ("Donnez moi deux nombres ? additionner:");
    scanf ("%d %d", &nb1, &nb2);

    la_requete.letype = 1;
    la_requete.nb1 = nb1;
    la_requete.nb2 = nb2;
    la_requete.pid = getpid(); 


    /* envoi de la requete */
    msgsnd(msqid, &la_requete, sizeof(struct requete)-4, 0);

    /* r?ception de la r?ponse */
    msgrcv(msqid, &la_reponse, sizeof(struct reponse)-4, la_requete.pid,0);


    printf ("le resultat re?u est: %d", la_reponse.res);
    msgctl (msqid, IPC_RMID, NULL);
    exit(0);
}

