package cpsc2150.listDec;

import java.util.List;
import java.util.Random;

public interface IShuffleList<T> extends List<T>
{
    // TODO: write contract
    default void shuffle(int swaps)
    {
        Random rand = new Random();
        for (int i = 0; i < swaps; i++)
        {
            // get two random indexes and swap them
            int a = rand.nextInt(size());
            int b;
            // randomize b til unique from a
            do
            {
                b = rand.nextInt(size());
            }
            while (b == a);
            swap(a, b);
        }
    }

    // TODO: write contract
    default void swap(int i, int j)
    {
    }
}
