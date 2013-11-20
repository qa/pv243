var View = {
    
    sendMessageForm : function( form ) {
        var message = {
            name: form.name.value,
            text: form.message.value
        };
        
        Rest.addMessage(message, function() {
            if (form.onload) {
                form.onload.call(form, message);
            }
        });
    },
    
    updateMessages : function( messages ) {
        messages.forEach(function(message) {
            View.addMessageToView(message);
        });
    },

    addMessageToView : function( message ) {
        var name = message.name;
        var text = message.text;
        
        var chat = document.getElementById('chat');
        var dt = document.createElement('dt');
        var dd = document.createElement('dd');
        dt.innerText = name;
        dd.innerText = text;
        chat.insertBefore(dd, chat.firstChild);
        chat.insertBefore(dt, chat.firstChild);
    },

    storeName: function() {
        var form = document.querySelector('form');
        localStorage.name = form.name.value;
    },

    restoreName: function() {
        var form = document.querySelector('form');
        if (localStorage.name) {
            form.name.value = localStorage.name;
        }
    }
};