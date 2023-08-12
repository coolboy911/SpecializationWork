import json
import datetime


def read_file(json_file):
    try:
        with open(json_file, "r") as file:
            data = json.load(file)
    except FileNotFoundError:
        data = {}
    return data


def print_note(id, data):
    print(json.dumps(data[id], indent=2, ensure_ascii=False))


def print_all_notes(data):
    for key, value in data.items():
        print("id: {}; title: {}; date: {}".format(
            value["id"], value["title"], value["date"]))


def add_note(data, json_file):
    title = input("введите заголовок: ")
    body = input("введите текст: ")
    note = {"id": str(len(data)+1), "date": str(datetime.datetime.now()),
            'title': title, 'body': body}
    data[str(len(data) + 1)] = note
    with open(json_file, "w") as file:
        json.dump(data, file, indent=4, ensure_ascii=False)


def edit_note(id, data, json_file):
    data[id]["title"] = input("введите заголовок: ")
    data[id]["body"] = input("введите текст: ")
    data[id]["date"] = str(datetime.datetime.now())
    with open(json_file, "w") as file:
        json.dump(data, file, indent=4, ensure_ascii=False)


def delete_note(id, data, json_file):
    del data[id]
    # fixing order of id, making it 1, 2, 3... again
    for i, key in enumerate(list(data.keys())):
        if key != i + 1:
            data[i + 1] = data.pop(key)
            data[i + 1]["id"] = str(i + 1)
    with open(json_file, "w") as file:
        json.dump(data, file, indent=4, ensure_ascii=False)


def search_by_date(data):
    start_year = int(input("Enter the starting year (YYYY): "))
    start_month = int(input("Enter the starting month (MM): "))
    start_day = int(input("Enter the starting day (DD): "))
    end_year = int(input("Enter the ending year (YYYY): "))
    end_month = int(input("Enter the ending month (MM): "))
    end_day = int(input("Enter the ending day (DD): "))
    matches = {}
    start_datetime = datetime.datetime(
        start_year, start_month, start_day, 0, 0, 0)
    end_datetime = datetime.datetime(end_year, end_month, end_day+1, 0, 0, 0)
    for key, value in data.items():
        date_format = "%Y-%m-%d %H:%M:%S.%f"
        note_datetime = datetime.datetime.strptime(value["date"], date_format)
        if start_datetime <= note_datetime < end_datetime:
            matches[key] = value
    print(json.dumps(matches, indent=2, ensure_ascii=False))


# main
json_file = 'notes.json'
flag = True
while (flag):
    data = read_file(json_file)
    print("1-показать все записки")
    print("2-показать заметку по id")
    print("3-добавить заметку")
    print("4-изменить заметку по id")
    print("5-удалить заметку")
    print("6-поиск по дате")
    print("7-завершить работу")
    match int(input()):
        case 1: print_all_notes(data)
        case 2:
            id = input("введите id: ")
            print_note(id, data)
        case 3: add_note(data, json_file)
        case 4:
            id = input("введите id: ")
            edit_note(id, data, json_file)
        case 5:
            id = input("введите id: ")
            delete_note(id, data, json_file)
        case 6: search_by_date(data)
        case 7: flag = False
        case default: print("неопознанная команда")
