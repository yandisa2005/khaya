var ActivitiRest = {

    options: {},

	getProcessDefinition: function(identifier, callback) {
		var url = Lang.sub(this.options.processDefinitionUrl, {identifier: identifier});

		$.ajax({
			url: url,
			dataType: 'json',
            cache: false,
			async: true,
			success: function(data, textStatus) {
				var processDefinitionDiagramLayout = data;
				if (!processDefinitionDiagramLayout) {
					console.error("Process definition diagram layout '" + identifier + "' not found");
					return;
				} else {
                    callback.apply({processDefinitionDiagramLayout: processDefinitionDiagramLayout});
				}
			}
		}).done(function(data, textStatus) {
		}).fail(function(jqXHR, textStatus, error){
			console.log('Get diagram layout['+processDefinitionId+'] failure: ', textStatus, jqXHR);
		});
	},

	getHighLights: function(callback) {
		var url = Lang.sub(this.options.processInstanceHighLightsUrl);

        $.ajax({
			url: url,
			dataType: 'json',
			cache: false,
			async: true,
			success: function(data, textStatus) {
				var highLights = data;
				if (!highLights) {
					return;
				} else {
					callback.apply({highLights: highLights});
				}
			}
		}).done(function(data, textStatus) {
		}).fail(function(jqXHR, textStatus, error){
		    console.log('Get HighLights['+url+'] failure: ', textStatus, jqXHR);
		});
	}
};
