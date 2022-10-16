mainPage = `<div class='todo'>
<div class='todo__input'>
    <input class='todo__text' type='text'>
    <span class='todo__add'></span>
</div>
<select class='todo__options'>
    <option value='active'>активные</option>
    <option value='completed'>завершённые</option>
    <option value='deleted'>удалённые</option>
</select>
<ul class='todo__items'></ul>
</div>`

function update(){
    xhr("POST", "api/tasks", {
        headers: {
            Token: localStorage.getItem('token'),
            Login: localStorage.getItem('login')
        }
    }, function (xhr){
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            var todoItems = document.querySelector(".todo__items");
            todoItems.replaceChildren();
            var tasks = JSON.parse(xhr.responseText);
            for (var i in tasks){
                todoItems.insertAdjacentHTML('beforeend', `<li class="todo__item" data-todo-state="${tasks[i].status}" data-todo-id="${tasks[i].id}">
                <span class="todo__task">
                  ${tasks[i].description}
                </span>
                <span class="todo__action todo__action_restore" data-todo-action="active"></span>
                <span class="todo__action todo__action_complete" data-todo-action="completed"></span>
                <span class="todo__action todo__action_delete" data-todo-action="deleted"></span></li>`)
            }
        }
        else {
            console.log('err', xhr.responseText);
        }
    })
    const option = document.querySelector('.todo__options').value;
    document.querySelector('.todo__items').dataset.todoOption = option;
    document.querySelector('.todo__text').disabled = option !== 'active';
}

function setState(elemItem){
    xhr("POST", "api/state", {
        headers: {
            Token: localStorage.getItem('token'),
            Login: localStorage.getItem('login')
        }, 
        body: JSON.stringify({
            id: elemItem.dataset.todoId,
            status: elemItem.dataset.todoState
        })
    }, function (xhr) {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            update();
        }
        else {
            console.log('err', xhr.responseText);
        }
    })
}

function del(elemItem){
    xhr("POST", "api/delete", {
        headers: {
            Token: localStorage.getItem('token'),
            Login: localStorage.getItem('login')
        }, 
        body: JSON.stringify({
            id: elemItem.dataset.todoId,
        })
    }, function (xhr) {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status === 200) {
            update();
        }
        else {
            console.log('err', xhr.responseText);
        }
    })
}

function add(){
    var todoText = document.querySelector('.todo__text');
    var text = todoText.value;
    if (text) {
        data = {
            description: text,
            status: 'active'
        };
        xhr("POST", "api/add", {
            headers: {
                Token: localStorage.getItem('token'),
                Login: localStorage.getItem('login')
            },
            body: JSON.stringify(data)
        }, function(xhr){
            if (xhr.readyState !== 4) {
                return;
            }
            if (xhr.status === 200) {
                update();
            }
            else {
                console.log('err', xhr.responseText);
            }
        })
    }
}

function loadMain(){
    setPage(mainPage, 'ui/style/main.css');
    document.querySelector('.todo__options').addEventListener('change', update);
    document.addEventListener('click', function (e) {
        var target = e.target;
        if (target.classList.contains('todo__action')){
            var action = target.dataset.todoAction;
            var elemItem = target.closest('.todo__item');
            if (action === 'deleted' && elemItem.dataset.todoState === 'deleted'){
                del(elemItem);
            } else {
                elemItem.dataset.todoState = action;
                setState(elemItem);
            }
        } else if (target.classList.contains("todo__add")) {
            add();
        }
    })
    update();
}

