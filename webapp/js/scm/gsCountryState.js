/**
 * This is used to display chain dropdown menu: country->state->city
 * 
 * @copyright GenScript Corp.
 */

var state = '';

// Country data table
//
// 
// To edit the list, just delete a line or add a line. Order is important. The
// order
// displayed is the order it appears on the drop down.
//
var country = '';

var inputTmpState = '';

// get state/county data through ajax
// syncronize the county state city data from php
var baseUrl = "${global_url}";
if (country == '') {
	$
			.ajax({
				async : false,
				type : "GET",
				url : baseUrl + "basedata/get_all_country_state.action",
				dataType : "json",
				success : function(msg) {
					if (msg.stateStr != '' && msg.countryStr != '') {
						state = msg.stateStr;
						country = msg.countryStr;
					} else {
						alert("System error! Please contact system administrator for help.");
					}
				},
				error : function(msg) {
					alert("System error! Please contact system administrator for help.");
					// refresh my search drop down list
				}
			});
}

function TrimString(sInString) {

	if (sInString) {

		sInString = sInString.replace(/^\s+/g, "");// strip leading
		return sInString.replace(/\s+$/g, "");// strip trailing
	}
}

// Populates the country select with the counties from the country list
function populateCountry() {
	var countryIdName = arguments[0][0];
	var defaultCountry = arguments[0][1];
	var countryChangeHandler = arguments[0][2];
	var stateIdName = arguments[0][3];
	var defaultState = arguments[0][4];
	var stateChangeHandler = arguments[0][5];
	// var cityIdName = arguments[0][6];
	// var defaultCity = arguments[0][7];
	// var cityChangeHandler = arguments[0][8];
	var tmpArray = [ countryIdName, defaultCountry, countryChangeHandler,
			stateIdName, defaultState, stateChangeHandler ];
    // alert(countryIdName);
	var countryLineArray = country.split('|'); // Split into lines
	var selObj = $('#' + countryIdName).get(0); // get the country select item

	selObj.options[0] = new Option('United States', 'US');
	selObj.selectedIndex = 0;

	for ( var loop = 0; loop < countryLineArray.length; loop++) {
		lineArray = countryLineArray[loop].split(':');
		countryCode = TrimString(lineArray[0]);
		countryName = TrimString(lineArray[1]);

		if (countryCode != '') {
			selObj.options[loop + 1] = new Option(countryName, countryCode);
		}

		if (defaultCountry == countryCode) {
			selObj.selectedIndex = loop + 1;
		}
	}
 
	// attach on change event to country dropdown menu
 $('#' + countryIdName).bind('change', function(e) {
		populateState(tmpArray);
		if (countryChangeHandler !== '') {
			eval(countryChangeHandler + "()");
		}
	 });
}

/**
 * stateIdName: the element id of state dropdown menu countryIdName: the element
 * id of country dropdown menu
 * 
 * @author LHC
 */
function populateState() {
	var countryIdName = arguments[0][0];
	var defaultCountry = arguments[0][1];
	var countryChangeHandler = arguments[0][2];
	var stateIdName = arguments[0][3];
	var defaultState = arguments[0][4];
	var stateChangeHandler = arguments[0][5]; 
	// var cityIdName = arguments[0][6];
	// var defaultCity = arguments[0][7];
	// var cityChangeHandler = arguments[0][8];
	var tmpArray = [ countryIdName, defaultCountry, countryChangeHandler,
			stateIdName, defaultState, stateChangeHandler ];

	// set cityIdName field to empty, TO BE REMOVED IN NEXT VERSION
	// $('#'+cityIdName).attr("value", defaultCity);
	//  alert(stateIdName);
	var selObj = $('#' + stateIdName).get(0);
 
	var foundState = false; // if there is some states for the country;

	// Empty options just in case new drop down is shorter, single select
	// dropdownmenu
	if (selObj.type == 'select-one') {
		selObj.options.length = 0; // remove all option from the select element
									// named 'selObj'
		selObj.options[0] = new Option('Select State', ''); // init the first
															// option element
		selObj.selectedIndex = 0; // set the default selected options;
	}
	// Populate the drop down with states from the selected country

	var stateLineArray = state.split("|"); // Split into lines
	var optionCntr = 1; //
	for ( var loop = 0; loop < stateLineArray.length; loop++) {
		lineArray = stateLineArray[loop].split(":"); // state line in the
														// variable 'state'
		countryCode = TrimString(lineArray[0]);
		stateCode = TrimString(lineArray[1]);
		
		stateName = TrimString(lineArray[2]);

		 /* alert(stateCode + "<<<<<<");
		  alert(stateName + "<<<<<<");*/
		if ($('#' + countryIdName).attr('value') == countryCode
				&& countryCode != '') {

			// If it's a input element, change it to a select
			if (selObj.type == 'text') {
				parentObj = $('#' + stateIdName).get(0).parentNode;
				parentObj.removeChild(selObj);

				var inputSel = document.createElement("SELECT");
				inputSel.setAttribute("name", stateIdName);
				inputSel.setAttribute("id", stateIdName);
			//alert(stateChangeHandler);
				// Added by LHC: for event handler
				if (stateChangeHandler !== '') {
					$(inputSel).bind('change', function(e) {
						eval(stateChangeHandler + "()");
					});
				}

				parentObj.appendChild(inputSel);
				selObj = $("#" + stateIdName).get(0);
				selObj.options[0] = new Option('Select State', '');
				selObj.selectedIndex = 0;
			}
			// state code abbreviation is empty
			//alert(stateCode);
			
			if (stateCode != '') {
			 	//alert(selObj);
			//	alert(optionCntr);
				selObj.options[optionCntr] = new Option(stateName, stateCode);
			}
			// See if it's selected from a previous post
			//
			if (stateCode == defaultState && countryCode == defaultCountry) {
				selObj.selectedIndex = optionCntr;
			}
			foundState = true; // find states for the country;
			optionCntr++; // counter - how many state in the country;
		}
	}
	// If the country has no states, change the select to a text box
	if (!foundState) {
		if (selObj.type == 'text')
			inputTmpState = selObj.value;
		parentObj = document.getElementById(stateIdName).parentNode;
		parentObj.removeChild(selObj);

		// Create the Input Field
		var inputEl = document.createElement("INPUT");

		inputEl.setAttribute("id", stateIdName);
		inputEl.setAttribute("type", "text");
		inputEl.setAttribute("name", stateIdName);
		inputEl.setAttribute("size", 20);
		// inputEl.setAttribute("value", inputTmpState ); //Taowei modify
		// (2010-09-01)
		inputEl.setAttribute("value", defaultState); // Taowei modify
														// (2010-09-01)
		inputEl.setAttribute("class", "NFText");
		inputEl.style.width = '131px';
		inputEl.style.height = '15px';
		inputEl.style.padding = '0';
		if (stateChangeHandler !== '') {
			$(inputEl).bind('change', function(e) {
				eval(stateChangeHandler + "()");
			});
		}

		parentObj.appendChild(inputEl);
	}

}

function populateCity() {
	var countryIdName = arguments[0];
	var stateIdName = arguments[1];
	var cityIdName = arguments[2];

	// TODO, for city displaying

}

// Called when state drop down is changed
// TODO: used to other place
function updateState(countryIdNameIn) {
	for ( var loop = 0; loop < countryFieldCfgArray.length; loop++) {
		countryIdName = countryFieldCfgArray[loop];
		stateIdName = stateFieldCfgArray[loop];
		// Read the default value hidden fields
		defaultCountry = document.getElementById(countryDefaultCfgArray[loop]).value;
		defaultState = document.getElementById(stateDefaultCfgArray[loop]).value;
		if (countryIdNameIn == countryIdName) {
			populateState(stateIdName, countryIdName);
		}
	}
}

// Initialize the drop downs
function initCountry() {
	for ( var loop = 0; loop < countryIdNames.length; loop++) {
		var countryIdName = countryIdNames[loop];
		if (arguments.length == 1 && countryIdName != arguments[0]) {
			continue;
		}
		var defaultCountry = countryDefaults[loop];
		var countryChangeHandler = countryChangeHandlers[loop];

		var stateIdName = stateIdNames[loop];
		var defaultState = stateDefaults[loop];
		var stateChangeHandler = stateChangeHandlers[loop];

		// var cityIdName = cityIdNames[loop];
		// var defaultCity = cityDefaults[loop];
		// var cityChangeHandler = cityChangeHandlers[loop];
		var tmpArray = [ countryIdName, defaultCountry, countryChangeHandler,
				stateIdName, defaultState, stateChangeHandler ];

		populateCountry(tmpArray);
		populateState(tmpArray);
		// populateCity(tmpArray);
	}

}
