document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary

      if (this.$step.innerText == 5) {
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        const checkedCheckboxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);
        const spanElements = checkedCheckboxes.map(checkbox => {
          const label = checkbox.closest('label');
          return label.querySelector('.description');
        });

        const summaryCategory = document.getElementById("summaryCategory")

        let categories = ""
        spanElements.forEach(element => {
          categories = categories + element.innerText
          summaryCategory.innerText = categories
        });

        const summaryInstitution = document.getElementById("summaryInstitution")
        const checkedCheckbox = document.querySelector('[data-step="3"] input[type="radio"]:checked');
        const institutionName = checkedCheckbox.closest('.form-group--checkbox').querySelector('.title');

        summaryInstitution.innerText = 'Dla fundacji "' + institutionName.innerText +'"'

        const summaryStreet = document.getElementById("summaryStreet");
        const summaryCity  = document.getElementById("summaryCity")
        const summaryZipCode = document.getElementById("summaryZipCode")
        const summaryPhoneNumber = document.getElementById("summaryPhoneNumber")
        const summaryDate = document.getElementById("summaryDate")
        const summaryTime = document.getElementById("summaryTime")
        const summaryComment = document.getElementById("summaryComment")

        const formStreet = document.getElementById("formStreet");
        const formCity = document.getElementById("formCity")
        const formZipCode =document.getElementById("formZipCode")
        const formPhoneNumber =  document.getElementById("formPhoneNumber")
        const formDate = document.getElementById("formDate")
        const formTime = document.getElementById("formTime")
        const formComment = document.getElementById("formComment")

        summaryStreet.innerText = formStreet.value;
        summaryCity.innerText = formCity.value;
        summaryZipCode.innerText = formZipCode.value;
        summaryPhoneNumber.innerText = formPhoneNumber.value;
        summaryDate.innerText = formDate.value;
        summaryTime.innerText = formTime.value;
        summaryComment.innerText = formComment.value;

      }






    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
