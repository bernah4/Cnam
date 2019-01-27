#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

int main()
{
    int ret;
    ret = fork();
    if (ret == 0)
        execlp("ls","ls","-l","/Users/Ordinatron/Desktop/CNAMinfo/NSY103",NULL);
    else
    {
        printf("Je suis le pere de PiD %d, mon fils est %d.\n",getpid(),getppid());
        wait(0);
        }
}

