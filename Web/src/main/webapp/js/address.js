/* 	load data about provinces, districts and wards in VietName */
			fetch("json/data.json").then(res => res.json())
		    .then(data => {
		        var option_str = document.getElementById('country');
		        option_str.length = 0;
		        option_str.options[0] = new Option('Chọn Tỉnh/TP', '');
		
		        option_str.selectedIndex = 0;
		        for (var i = 0; i < data.length; i++) {
		            option_str.options[option_str.length] = new Option(data[i].Name, data[i].Name);
		        }
		   
		    })
			function print_state(state_id, state_index) {
			    fetch("json/data.json").then(res => res.json())
			        .then(data => {
			        	 var option_str = document.querySelector('#district');
			            option_str.length = 0;
			            option_str.options[0] = new Option('Chọn Quận/Huyện', '');
			            option_str.selectedIndex = 0;
			            if (state_index > 0) {
			                for (var i = 0; i < data[state_index - 1].Districts.length; i++) {
			                    option_str.options[option_str.length] = new Option(data[state_index - 1].Districts[i].Name, data[state_index - 1].Districts[i].Name);
			                }
			            }
			
			        })
			}
				function print_district(district_id, district_index) {
				    fetch("json/data.json").then(res => res.json())
				        .then(data => {
				            var option_str = document.getElementById('ward');
				            option_str.length = 0;
				            option_str.options[0] = new Option('Chọn Phường/Xã', '');
				            option_str.selectedIndex = 0;
				            var state_index = document.getElementById('country').selectedIndex;
				            if (state_index > 0) {
				                state_index = state_index - 1;
				                if (district_index > 0) {
				                    for (var i = 0; i < data[state_index].Districts[district_index - 1].Wards.length; i++) {
				                        var index = data[state_index].Districts[district_index - 1].Wards[i];
				                        option_str.options[option_str.length] = new Option(index.Name, index.Name);
				                    }
				                }
				            }
				
				        })
		
		}