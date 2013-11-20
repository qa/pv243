var Rest = {
    
    context: function(path) {
        return document.location.pathname.replace(/[^/]*$/, '') + 'api/v1' + path;
    },
    
    addMessage : function( message, callback ) {
        var xhr = new XMLHttpRequest();
        xhr.open('PUT', this.context('/messages'), true);
        xhr.setRequestHeader("content-type", "application/json");
        xhr.onload = function() {
            callback.call(this);
        }; 
        xhr.send(JSON.stringify(message));
    },

    retrieveMessages : function( callback ) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', this.context('/messages'), true);
        console.log(this.context);
        xhr.onload = function() {
            var messages = JSON.parse(this.responseText);
            callback.call(xhr, messages);
        };
        xhr.send();            
    }
};