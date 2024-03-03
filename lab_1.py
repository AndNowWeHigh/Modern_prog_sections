def task(lst):
    tuple1 = {}
    first_type = type(lst[0])

    if all(type(elem) == first_type for elem in lst):
        for word in lst:
            sorted_word = ''.join(sorted(word))
            if sorted_word in tuple1:
                tuple1[sorted_word].append(word)
            else:
                tuple1[sorted_word] = [word]

        return (list(tuple1.values()))
    else:
        raise TypeError('Усі елементи мають бути рядками (типу str)')

print(task(['tar', 'rat', 'arrr', 'rrar', 'tarr']))
