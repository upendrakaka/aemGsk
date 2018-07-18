(function () {
    var DATA_EAEM_NESTED = "data-eaem-nested";
    var CFFW = ".coral-Form-fieldwrapper";

    //reads multifield data from server, creates the nested composite multifields and fills them
    function addDataInFields() {
        $(document).on("dialog-ready", function() {
            var $fieldSets = $("[" + DATA_EAEM_NESTED + "][class='coral-Form-fieldset']");

            if(_.isEmpty($fieldSets)){
                return;
            }

            var mNames = [];

            $fieldSets.each(function (i, fieldSet) {
                mNames.push($(fieldSet).data("name"));
            });

            mNames = _.uniq(mNames);

            var actionUrl = $fieldSets.closest("form.foundation-form").attr("action") + ".json";

            $.ajax(actionUrl).done(postProcess);

            function postProcess(data){
                _.each(mNames, function(mName){
                    buildMultiField(data, mName);
                });
            }

            //creates & fills the nested multifield with data
            function fillNestedFields($multifield, valueArr){
                _.each(valueArr, function(record, index){
                    $multifield.find(".js-coral-Multifield-add").click();

                    //a setTimeout may be needed
                    _.each(record, function(value, key){
                        var $field = $($multifield.find("[name='./" + key + "']")[index]);
                        $field.val(value);
                    })
                })
            }

            function buildMultiField(data, mName){
                if(_.isEmpty(mName)){
                    return;
                }

                $fieldSets = $("[data-name='" + mName + "']");

                //strip ./
                mName = mName.substring(2);

                var mValues = data[mName], $field, name;

                if(_.isString(mValues)){
                    mValues = [ JSON.parse(mValues) ];
                }

                _.each(mValues, function (record, i) {
                    if (!record) {
                        return;
                    }

                    if(_.isString(record)){
                        record = record.replace(/(?:\r\n|\r|\n)/g, '');
                        record = JSON.parse(record);
                    }

                    _.each(record, function(rValue, rKey){
                        $field = $($fieldSets[i]).find("[name='./" + rKey + "']");

                        if(_.isArray(rValue) && !_.isEmpty(rValue)){
                            fillNestedFields( $($fieldSets[i]).find("[data-init='multifield']"), rValue);
                        }else{
							var selector = $field.selector;
                            //if selector contains 'Description' means, field is rich text and need to call method text to set the text
                            if (selector && selector.indexOf("ListTOneDesc") >= 0){
                                $field.text(rValue);
                                //set value for the input text
                                $field.val(rValue);
                                //value that will be seen by user on the UI
                                $field.next().next().html(rValue);
                            } else{
                                $field.val(rValue);
                            }
                        }
                    });
                });
            }
        });
    }

    function fillValue($field, record){
        var name = $field.attr("name");

        if (!name) {
            return;
        }

        //strip ./
        if (name.indexOf("./") == 0) {
            name = name.substring(2);
        }
		var value = $field.val();
        value = value.replace('\n', '');
        value = value.replace('\r', '');
        record[name] = value;

        //remove the field, so that individual values are not POSTed
        $field.remove();
    }

    //for getting the nested multifield data as js objects
    function getRecordFromMultiField($multifield){
        var $fieldSets = $multifield.find("[class='coral-Form-fieldset']");

        var records = [], record, $fields, name;

        $fieldSets.each(function (i, fieldSet) {
            $fields = $(fieldSet).find("[name]");

            record = {};

            $fields.each(function (j, field) {
                fillValue($(field), record);
            });

            if(!$.isEmptyObject(record)){
                records.push(record)
            }
        });

        return records;
    }

    //collect data from widgets in multifield and POST them to CRX as JSON
    function collectDataFromFields(){
        $(document).on("click", ".cq-dialog-submit", function () {
            var $form = $(this).closest("form.foundation-form");
            var $fieldSets = $("[" + DATA_EAEM_NESTED + "][class='coral-Form-fieldset']");
            var record, $fields, $field, name, $nestedMultiField;

            $fieldSets.each(function (i, fieldSet) {
                $fields = $(fieldSet).children().children(CFFW);

                record = {};

                $fields.each(function (j, field) {
                    $field = $(field);

                    //may be a nested multifield
                    $nestedMultiField = $field.find("[data-init='multifield']");

                    if($nestedMultiField.length == 0){
                        fillValue($field.find("[name]"), record);
                    }else{
                        name = $nestedMultiField.find("[class='coral-Form-fieldset']").data("name");

                        if(!name){
                            return;
                        }

                        //strip ./
                        name = name.substring(2);

                        record[name] = getRecordFromMultiField($nestedMultiField);
                    }
                });

                if ($.isEmptyObject(record)) {
                    return;
                }

                //add the record JSON in a hidden field as string
                $('<input />').attr('type', 'hidden')
                    .attr('name', $(fieldSet).data("name"))
                    .attr('value', JSON.stringify(record))
                    .appendTo($form);
            });
        });
    }

    $(document).ready(function () {
        addDataInFields();
        collectDataFromFields();
    });

})();
