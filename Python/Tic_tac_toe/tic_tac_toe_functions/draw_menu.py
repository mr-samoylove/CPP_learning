from tkinter import Menu
from tkinter import messagebox


def draw_menu(window, menu):
    new_item = Menu(menu, tearoff=0)
    new_item.add_command(label='Автор', command=draw_author)
    menu.add_cascade(label='Меню', menu=new_item)
    window.config(menu=menu)


def draw_author():
    messagebox.showinfo(title='Author', message='mr.samoylove\n\nhave fun')
