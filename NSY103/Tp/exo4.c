#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

int     main()
{
    int      ret;
    int      pip[2];
 //   int      fp[2];
    char    msg[28];
    
    if(pipe(pip))
        return (-1);
    ret = fork();
    if(ret != 0)
    {
        sprintf(msg,"Hello Luke je suis ton pere");
        write(pip[1],msg,28);
        close(pip[1]);
        wait(0);
        read(pip[0],msg,28);
        printf("%s\n",msg);
        close(pip[0]);
    }
    else
    {
        read(pip[0],msg,28);
        printf("%s\n",msg);
        close(pip[0]);
        sprintf(msg,"Nooon");
        write(pip[1],msg,6);
        close(pip[1]);
    }
    return (0);
}
