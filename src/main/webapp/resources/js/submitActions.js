var button = document.querySelector('#solve');
var decInput = document.querySelector('#dec');
var binInput = document.querySelector('#bin');
var form = document.querySelector('#input');
var last = document.querySelector('#last');

decInput.onkeyup = function (){
    handleChangingInput(this,binInput,"dec");
};

binInput.onkeyup = function (){
    handleChangingInput(this,decInput,"bin");
};

function handleChangingInput(field, disabledField, lastChangedParam){
    if(field.value !== ""){
        last.value = lastChangedParam;
    }
    if(disabledField.value === "" && field.value !== ""){
        disabledField.disabled = true;
    }else{
        disabledField.disabled = false;
    }
}

button.onclick = function (){
    if(decInput.value.trim() === "" && binInput.value.trim() === ""){
        alert("Please enter a binary or decimal number!");
    }else{
        handleRemovedInput();
        form.submit();   
    }
};

function handleRemovedInput(){
    var binEmpty = binInput.value === "";
    var decEmpty = decInput.value === "";
    if(binEmpty || decEmpty){
        if(binEmpty){
            last.value = "dec";
        }else if(decEmpty){
            last.value = "bin";
        }else{
            alert("error: " + binInput.value + ", " + decInput.value);
        }
    }
}
