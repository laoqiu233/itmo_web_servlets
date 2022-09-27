const form = document.getElementById('form');
const submitButton = document.getElementById('form-submit');
const xWarning = document.getElementById('input-x-warning');
const yWarning = document.getElementById('input-y-warning');
const rWarning = document.getElementById('input-r-warning');
const floatRegex = /^[+-]?\d+(?:\.\d+)?$/;

function setWarning(el, text) {
    if (text === '') {
        el.hidden = true;
    } else {
        el.hidden = false;
        el.innerHTML = text;
    }
}

function validateX(x) {
    setWarning(xWarning, '');

    if (floatRegex.test(x) && parseFloat(x) >= -3 && parseFloat(x) <= 5) {
        return true;
    } else {
        setWarning(xWarning, 'X should be a float between -3 and 5');
        return false;
    }
}

function validateY(y) {
    setWarning(yWarning, '');
    if (y === null || !floatRegex.test(y)) {
        console.log('y', y, floatRegex.test(y));
        setWarning(yWarning, 'Please select a value for Y');
        return false;
    }
    return true;
}

function validateR(r) {
    setWarning(rWarning, '');
    if (r === null || !floatRegex.test(r)) {
        console.log('r', r, floatRegex.test(r));
        setWarning(rWarning, 'Please select a value for R');
        return false;
    }
    return true;
}

function validateAll() {
    const formData = new FormData(document.getElementById('form'));

    console.log('x', formData.get('x'));
    console.log('y', formData.get('y'));
    console.log('r', formData.get('r'));
    
    const xValid = validateX(formData.get('x'));
    const yValid = validateY(formData.get('y'));
    const rValid = validateR(formData.get('r'));

    if (xValid && yValid && rValid) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

document.getElementById('form').addEventListener('change', validateAll);
document.getElementById('input-x').addEventListener('input', validateAll);
document.getElementById('input-y').addEventListener('input', validateAll);
validateAll();