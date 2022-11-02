bytes_dict = {'B': 1, 'KB': 1024, 'MB': 1024 ** 2, 'GB': 1024 ** 3}

with open("files.txt", encoding='utf-8') as f:
    l = [s.replace('.', ' ').split() for s in f.readlines()]

l.sort(key=lambda el: el[1])

all_extensions = set([el[1] for el in l])

for exten in sorted(all_extensions):
    total_bytes = 0
    list_of_names = []
    measure = 'B'
    while l and l[0][1] == exten:
        name, _, amount, measure = l.pop(0)
        list_of_names.append(name + '.' + exten)
        total_bytes += int(amount) * bytes_dict[measure]
    print(*sorted(list_of_names), sep='\n')

    if total_bytes > 1024:
        total_bytes /= 1024
        measure = 'KB'
        if total_bytes > 1024:
            total_bytes /= 1024
            measure = 'MB'
            if total_bytes > 1024:
                total_bytes /= 1024
                measure = 'GB'

    print("----------")
    print(f"Summary: {round(total_bytes)} {measure}\n")
