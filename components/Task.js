app.component("task",{
    props:{
        index:{
            type: Number,
            required: true
        },
        data:{
            type: Object,
            required: true
        }

    },
    template: 
    /*html*/
    `   
        <div 
            class = "task-container"
            :class = "this.color"
            @mouseover = "handleHover" 
            @mouseleave = "handleHoverLeave"
            @click = "notifyClick">
            
            <h3>{{data.name}}</h3>
            <svg v-if="hovered" class="deleteIcon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="black" width="18px" height="18px"><path d="M0 0h24v24H0z" fill="none"/><path d="M0 0h24v24H0V0z" fill="none"/><path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zm2.46-7.12l1.41-1.41L12 12.59l2.12-2.12 1.41 1.41L13.41 14l2.12 2.12-1.41 1.41L12 15.41l-2.12 2.12-1.41-1.41L10.59 14l-2.13-2.12zM15.5 4l-1-1h-5l-1 1H5v2h14V4z"/></svg>
            <p v-if="data.date"> Due: {{data.date}}</p>
            <p v-else="data.date">No Due Date</p>
        
        </div>
    `,
    data(){
        return {
            hovered:false
        }
    },
    methods:{
        notifyClick(){
            this.$emit("task-clicked",this.index)
        },
        handleHover(){
            this.hovered = true 
        },
        handleHoverLeave(){
            this.hovered = false 
        }
    },
    computed:{
        color(){
            let color = "";
            console.log(this.data.date)
            let difference = (new Date()).getTime() - Date.parse(this.data.date);
            difference =  difference  / (1000 * 3600 * 24)
            console.log(difference)
            difference = isNaN(difference) ? 10: difference
            switch(this.data.priority){
                case 1:
                    color= "lowTask";
                    if(difference >= 7 ){
                        break; 
                    }
                case 2:
                    color= "medTask";
                    if(difference >= 2){
                        break; 
                    }
                case 3:
                    color= "highTask";
                    break;
            }
            return color; 
        }
    }

})