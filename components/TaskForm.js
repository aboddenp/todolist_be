app.component('task-form',{
    props:{
        show: {
            type: Boolean,
            required: true 
        }
    },
    template: 
    /*html*/`
    <form   autocomplete="off" v-if = "show" class = "task-form" @submit.prevent ="onSubmit">
    <h1>New Task</h1>
    <label for="name"  autocomplete='off'>Task Name</label>
    <input  id = "name" v-model="taskName" maxlength="40" placeholder = "Buy Groceries" type="text " required>
    <br> 
    <label for="priority">Priority</label>
    <select id="priority"  v-model.number="priority" name="priority" class = "input" >
        <option value="0">none</option>
        <option value="1" selected= "true">low</option>
        <option value="2">med</option>
        <option value="3">high</option>
    </select>
    <br> 
    <label for="date">Reminder</label>
    <input type="date" v-model="due">
    <input type="submit" class = "button">
</form>`,
data(){
    return {
        taskName: "",
        priority: 0,
        due: null 
    }
},
methods:{
    onSubmit(){
        // return the object with the information 
        let task = {
            name: this.taskName,
            date: this.due,
            priority: this.priority

        };
        this.taskName ="";
        this.priority = 0; 
        this.due = null;
        this.$emit("new-task-submitted",task);
    }
}

})