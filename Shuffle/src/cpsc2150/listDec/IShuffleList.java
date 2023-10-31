/*
    Team: Isaiah Pham, Korey Bryant, Ben Nazaruk, Kaylee Pierce
 */
package cpsc2150.listDec;

import java.util.List;
import java.util.Random;

public interface IShuffleList<T> extends List<T>
{
    /**
     * Shuffles the elements in the list by randomly picking two positions and swapping them.
     *
     * @param swaps
     *          the number of times to shuffle the list
     *
     * @pre swaps >= 0
     * @post The elements in the list are randomly shuffled 'swaps' times.
     */
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

    /**
     * Swaps the elements at positions 'i' and 'j' in the list.
     *
     * @param i the index of the first element to swap
     * @param j the index of the second element to swap
     *
     * @pre 0 <= i < size() && 0 <= j < size()
     * @post The elements at positions 'i' and 'j' have been swapped.
     */
    default void swap(int i, int j)
    {
    }
}
