const app = Vue.createApp({
    data(){
        return {
            formActive:false,
            tasks: [ {name:"Buy Groceries", date: new Date().toLocaleDateString(), priority:0 }]
        }
    },
    methods: {
        RemoveItem(index){
            this.tasks.splice(index,1)
        },
        addTask(task){
            this.tasks.push(task)
            this.formActive = false; 
        },
        openForm(){
            this.formActive = true ; 
        }
    }
});