/**
 * This script assumes there is a global FORM_DATA variable
 * Creates a form and applies validation.
 */

const form = document.getElementById('form');
const floatRegex = /^[+-]?\d+(?:\.\d+)?$/;

// Validators
function floatInRange() {
    this.setWarning('');

    if (floatRegex.test(this.getValue()) && parseFloat(this.getValue()) >= this.min && parseFloat(this.getValue()) <= this.max) {
        return true;
    } else {
        this.setWarning(`${this.displayName} should be a float between ${this.min} and ${this.max}`);
        return false;
    }
}

function notNull() {
    this.setWarning('');

    if (this.getValue() === null || !floatRegex.test(this.getValue())) {
        this.setWarning(`Please select a value for ${this.displayName}`);
        return false;
    }
    return true;
}

/**
 * 
 * @param {*} data Object containing data about this element
 * @param {string} id ID of the element that contains actual value.
 * @param {string} formName The name of this element in the form
 * @param {string} displayName The name to display this property as.
 * @returns 
 */
function generateFormElement(data, id, formName, displayName) {
    // Common warning element
    const warningEl = document.createElement('p');
    warningEl.id = `${id}-warning`;
    warningEl.className = 'warning';
    warningEl.hidden = true;

    // A validator object for this form element
    const validator = {
        displayName: displayName,
        setWarning: (text) => {
            if (text === '') {
                warningEl.hidden = true;
            } else {
                warningEl.hidden = false;
                warningEl.innerHTML = text;
            }
        }
    };
    const root = document.createElement('div');
    root.className = 'row';

    if (data.type === 'text') {
        const label = document.createElement('label');
        label.htmlFor = id;
        label.innerHTML = displayName;
        root.appendChild(label);

        const innerDiv = document.createElement('div');

        const input = document.createElement('input');
        input.type = 'text';
        input.name = formName;
        input.id = id;
        input.placeholder = `${data.min}...${data.max}`;
        input.maxLength = 15;
        
        innerDiv.appendChild(input);
        innerDiv.appendChild(warningEl);
        root.appendChild(innerDiv);

        // Add parameters for floatInRange validator
        validator.min = data.min;
        validator.max = data.max;
        validator.getValue = () => input.value;
        validator.validate = floatInRange;
    } else if (data.type === 'select') {
        const label = document.createElement('label');
        label.htmlFor = `${id}-select`;
        label.innerHTML = displayName;
        root.appendChild(label);

        const select = document.createElement('select');
        select.name = formName;
        select.id = `${id}-select`;

        // Hidden choice for graph click
        const hiddenChoice = document.createElement('option');
        hiddenChoice.id = id;
        hiddenChoice.value = '';
        hiddenChoice.hidden = true;
        select.appendChild(hiddenChoice);

        data.choices.forEach((v) => {
            const option = document.createElement('option');
            option.value = v;
            option.innerHTML = v;
            select.appendChild(option);
        });

        const innerDiv = document.createElement('div');
        innerDiv.appendChild(select);
        innerDiv.appendChild(warningEl);
        root.appendChild(innerDiv);

        validator.getValue = () => new FormData(form).get(formName);
        validator.validate = notNull;
    } else if (data.type === 'radio') {
        const label = document.createElement('label');
        label.innerHTML = displayName;
        root.appendChild(label);

        const optionsDiv = document.createElement('div');

        // Hidden choice for graph click
        const hiddenOption = document.createElement('input');
        hiddenOption.type = 'radio';
        hiddenOption.name = formName;
        hiddenOption.id = id;
        hiddenOption.value = '';
        hiddenOption.hidden = true;

        optionsDiv.appendChild(hiddenOption);

        data.choices.forEach((v, i) => {
            const row = document.createElement('row');
            row.className = 'row';

            const input = document.createElement('input');
            input.type = 'radio';
            input.name = formName;
            input.id = `${formName}-${i}`;
            input.value = v;

            const label = document.createElement('label');
            label.htmlFor = input.id;
            label.innerHTML = v;

            row.appendChild(input);
            row.appendChild(label);

            optionsDiv.appendChild(row);
        });

        const innerDiv = document.createElement('div');
        innerDiv.appendChild(optionsDiv);
        innerDiv.appendChild(warningEl);
        root.appendChild(innerDiv);

        validator.getValue = () => new FormData(form).get(formName);
        validator.validate = notNull;
    }

    form.appendChild(root);

    return validator;
}

function createSubmitButton() {
    const row = document.createElement('div');
    row.className = 'row';
    const button = document.createElement('button');
    button.id = 'form-submit';
    button.className = 'row-fill';
    button.type = 'submit';
    button.innerHTML = 'Send';
    row.appendChild(button);
    form.appendChild(row);

    return button;
}

const formValidator = {}

formValidator.xValidator = generateFormElement(FORM_DATA['xInput'], 'input-x', 'x', 'X');
formValidator.yValidator = generateFormElement(FORM_DATA['yInput'], 'input-y', 'y', 'Y');
formValidator.rValidator = generateFormElement(FORM_DATA['rInput'], 'input-r', 'r', 'R');
const submitButton = createSubmitButton();

formValidator.validateAll = function() {
    console.log('x', this.xValidator.getValue());
    console.log('y', this.yValidator.getValue());
    console.log('r', this.rValidator.getValue());

    const xValid = this.xValidator.validate();
    const yValid = this.yValidator.validate();
    const rValid = this.rValidator.validate();

    submitButton.disabled = !(xValid && yValid && rValid);
}

document.getElementById('form').addEventListener('change', () => formValidator.validateAll());
// In case if any of the elements are not change based but input based. (Like text input)
document.getElementById('input-x').addEventListener('input', () => formValidator.validateAll());
document.getElementById('input-y').addEventListener('input', () => formValidator.validateAll());
document.getElementById('input-r').addEventListener('input', () => formValidator.validateAll());
formValidator.validateAll();