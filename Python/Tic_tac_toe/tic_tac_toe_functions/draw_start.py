from tkinter.ttk import Combobox
from tic_tac_toe_functions.game_logic import *


def draw_start_and_play(window):
    def click_start_button_and_begin():
        if len(name.get()):
            name.insert(0, ", ")
        header.configure(text=f"  Жмакайте{name.get()}")
        start_button.grid_remove()
        name.grid_remove()
        name_asker.grid_remove()
        marker_asker.grid_remove()
        marker.grid_remove()

        play(window, marker.get())  # this is where magic starts

    header = Label(window,
                   text="  Добро пожаловать в игру",
                   font=("Roboto thin", 50),
                   height=2)
    header.grid(column=0, row=0)

    name_asker = Label(window,
                       text="Введите ваше имя (необязательно) ------->",
                       font=("Roboto thin", 20))
    name_asker.grid(column=0, row=1)
    marker_asker = Label(window,
                         text="Кто ходит первый? (Вы играете крестиками) ------->",
                         font=("Roboto thin", 20))
    marker_asker.grid(column=0, row=2)

    name = Entry(window, width=20, font=("Roboto thin", 22))
    name.grid(column=1, row=1)
    name.focus()

    marker = Combobox(window, font=("Roboto thin", 20))
    marker['values'] = ("Компьютер", "Человек")
    marker.current(0)
    marker.grid(column=1, row=2)

    start_button = Button(window,
                          text="Начать",
                          font=("Roboto thin", 20),
                          bg='grey', fg='white',
                          command=click_start_button_and_begin,
                          height=2,
                          width=10)
    start_button.grid(column=0, row=3)
