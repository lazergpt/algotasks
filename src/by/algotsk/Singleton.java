package by.algotsk;

import java.util.Arrays;

// потокобезопасен, тяжёлый объект создаётся заранее, мб он и не нужен
class Singleton
{
    private static final Singleton singleton = new Singleton();

    private Singleton(){}

    public static Singleton getSingleton()
    {
        return singleton;
    }
}

class ThreadSafeSingleton
{
    private static volatile ThreadSafeSingleton threadSafeSingleton = null;

    private ThreadSafeSingleton()
    {
    }

    public static ThreadSafeSingleton getThreadSafeSingleton()
    {
        if (threadSafeSingleton == null)
        {
            synchronized(ThreadSafeSingleton.class) {
                if(threadSafeSingleton == null)
                {
                    threadSafeSingleton = new ThreadSafeSingleton();
                }

            }
        }

        return threadSafeSingleton;
    }
}
