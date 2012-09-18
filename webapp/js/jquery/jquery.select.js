/**
 * File Name: jquery.select.js
 * Functions: This plugin is used to manipulate select box
 * Author:John Liu
 * Date: 03/12/2008
 **/

// get the length of select box
jQuery.fn.size = function()
{
    return jQuery(this).get(0).options.length;
}

// get the selected index
jQuery.fn.getSelectedIndex = function()
{
    return jQuery(this).get(0).selectedIndex;
}

// get the selected text
jQuery.fn.getSelectedText = function()
{
    if(this.size() == 0)
    {
        return "The select box is empty";
    }
    else
    {
        var index = this.getSelectedIndex();      
        return jQuery(this).get(0).options[index].text;
    }
}

// get the selected item value
jQuery.fn.getSelectedValue = function()
{    
    if(this.size() == 0)
    {
        return "No selected option";
    }
    else
    {
        return jQuery(this).val();
    }
}

// set selected value in select box
jQuery.fn.setSelectedValue = function(value)
{
    jQuery(this).get(0).value = value;
}

// set the first matched text as selected
jQuery.fn.setSelectedText = function(text)
{
    var isExist = false;
    var count = this.size();
    for(var i=0;i<count;i++)
    {
        if(jQuery(this).get(0).options[i].text == text)
        {
            jQuery(this).get(0).options[i].selected = true;
            isExist = true;
            break;
        }
    }
    if(!isExist)
    {
        alert("Not matched select item");
    }
}

// set selected index
jQuery.fn.setSelectedIndex = function(index)
{
    var count = this.size();    
    if(index >= count || index < 0)
    {
        alert("Index exceeds");
    }
    else
    {
        jQuery(this).get(0).selectedIndex = index;
    }
}

// judge if the value is in select box
jQuery.fn.isExistItem = function(value)
{
    var isExist = false;
    var count = this.size();
    for(var i=0;i<count;i++)
    {
        if(jQuery(this).get(0).options[i].value == value)
        {
            isExist = true;
            break;
        }
    }
    return isExist;
}

// add option to select box, duplicated value will be alert
jQuery.fn.addOption = function(text,value)
{
    if(this.isExistItem(value))
    {
        alert("Duplicated item value");
    }
    else
    {
        jQuery(this).get(0).options.add(new Option(text,value));
    }
}

// delete the option of select according to value
jQuery.fn.removeItem = function(value)
{    
    if(this.isExistItem(value))
    {
        var count = this.size();        
        for(var i=0;i<count;i++)
        {
            if(jQuery(this).get(0).options[i].value == value)
            {
                jQuery(this).get(0).remove(i);
                break;
            }
        }        
    }
    else
    {
        alert("The item to be removed does not exist");
    }
}

// remove the select item according to index
jQuery.fn.removeIndex = function(index)
{
    var count = this.size();
    if(index >= count || index < 0)
    {
        alert("No matched item for removing");
    }
    else
    {
        jQuery(this).get(0).remove(index);
    }
}

// remove selected option
jQuery.fn.removeSelected = function()
{
    var index = this.getSelectedIndex();
    this.removeIndex(index);
}

// clear all select options
jQuery.fn.clearAll = function()
{
    jQuery(this).get(0).options.length = 0;
}