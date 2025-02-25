# Uncle Roger User Guide

---
## Say Hi to Uncle Roger

Uncle Roger is a simple and intuitive task management application designed to help you keep track of your tasks and deadlines. 
Whether you're a student, a professional, or just someone looking to stay organized, Uncle Roger is here to assist you.
---
## Adding Tasks
### Adding a Todo Task

To add a Todo task, simply type `todo` followed by the description of the task.

Example: `todo buy groceries`

### Expected output:
```
Ok. Uncle Roger add this task for you:
  [T][ ] Buy groceries
Don't forget, now you have 1 tasks in your list.
```

### Adding a Deadline Task

To add a Deadline task, type `deadline` followed by the description of the task 
and the deadline using the `/by` keyword.

Example: `deadline weekly CS2113 quiz /by Monday 9pm`

### Expected output:
```
Ok. Uncle Roger add this task for you:
  [D][ ] weekly CS2113 quiz (by: Monday 9pm)
Don't forget, now you have 2 tasks in your list.
```

### Adding an Event Task

To add an Event task, type `event` followed by the description of the task, 
the start time using the `/from` keyword, and the end time using the `/to` keyword.

Example: `event CS2113 lecture /from Friday 4 /to 6pm`

### Expected output:
```
Ok. Uncle Roger add this task for you:
  [E][ ] CS2113 lecture (from: Friday 4 to: 6pm)
Don't forget, now you have 3 tasks in your list.
```

## Managing tasks
### Listing all tasks

To view all your tasks, simply type `list`.

Example: `list`

### Expected output:
```
Come. Uncle Roger remind you what tasks you have: 
1.[T][ ] buy groceries
2.[D][ ] weekly CS2113 quiz (by: Monday 9pm)
3.[E][ ] CS2113 lecture (from: Friday 4 to: 6pm)
```

### Marking a Task as Done

To mark a task as done, type `mark` followed by the task number.

Example: `mark 1`

### Expected output:
```
Uncle Roger help you mark this as done:
  [T][X] buy groceries
Fuiyoh! Uncle Roger proud of you, good job.
```

### Unmarking a Task

To unmark a task, type `unmark` followed by the task number.

Example: `unmark 1`

### Expected output:
```
Uncle Roger help you mark this as undone:
  [T][ ] buy groceries
Haiya...Uncle Roger disappointed in you, don't be lazy!
```

### Deleting a Task

To delete a task, type `delete` followed by the task number.

Example: `delete 1`

### Expected output:
```
Ok. Uncle Roger help you remove this task:
  [T][ ] buy groceries
Now you have 2 tasks in your list.
```

### Finding a Task

To find a task by description, type `find` followed by 
the substring to look for in the tasks' descriptions

Example: `find cs2113`

### Expected output:
```
Uncle Roger found these tasks for you.
Better say thank you to Uncle Roger.
1:[D][ ] weekly CS2113 quiz (by: Monday 9pm)
2:[E][ ] CS2113 lecture (from: Friday 4 to: 6pm)
```

### Exiting the Application

To exit Uncle Roger, simply type `bye`.

Example: `bye`

### Expected output:
```
Haiya...Goodbye.
Don't like Uncle Roger just say.
```
---
## Conclusion
Uncle Roger is designed to make your task management experience as smooth and enjoyable as possible with a little attitude. 
Whether you need to add tasks, mark them as done, or delete them, Uncle Roger is here to help. 
Happy organizing!
---