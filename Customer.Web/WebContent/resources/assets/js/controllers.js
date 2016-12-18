app.controller('mainCtrl', function($scope, Page){

	$scope.Page = Page;
	$scope.isCollapsed=true;
});

app.controller('customerCtrl', function($scope, Page, $http){
	
	Page.setTitle('Customer');
	
	$scope.sexs =[{sex:"Male"},{sex:"Female"}];
	
	_refreshPageData();
	
	function _refreshPageData(){
		var method = "GET";
		var url = "api/Customer/load"
		$http({
			method:method,
			url:url
		}).then(function(res){
			
			$scope.customers = res.data;
			
		})
	}
	
	$scope.customer = {
			id:" ",
			customerCode:" ",
			firstName:" ",
			lastName:" ",
			sex:" ",
			phoneNumber:" ",
			email:"",
			address:"",
			zipCode:"",
			dateEnter:""
	};
	
	$scope.saveAdd = function(){
		var method = "POST";
		var url = "api/Customer/insert";
		var data = $scope.customer;
		console.log(data);
		$http({
			method:method,
			url:url,
			data:data
		}).then(
				function(res){
					if(!res.data.success){
						swal("Error!",res.data.msg,"error");
					}
					else{
						$('#myModal').modal('hide');
						_refreshPageData();
					}
					
					
				}
				
		)
	}
	
	$scope.saveUpdate = function(){
		var method = "POST";
		var url = "api/Customer/update";
		var data = $scope.customer;
		console.log(data);
		$http({
			method:method,
			url:url,
			data:data
		}).then(
				function(res){
					if(res.data.success){
						swal("Error!",res.data.msg,"error");
					}
					else{
						$('#myModal').modal('hide');
						_refreshPageData();
					}
				}	
		)
	}
	
	$scope.removeCustomer = function(id){
		swal({  
			title: "Are you sure want to remove?",   
			text: "You will not be able to recover this!",   
			type: "warning",   
			showCancelButton: true,   
			confirmButtonColor: "#DD6B55",   
			confirmButtonText: "Yes, delete it!",   
			closeOnConfirm: true }, function(){   
				$http({
			    	   method : 'DELETE',
			    	   url : 'api/Customer/remove/'+id
			       }).then(function(res){
			    	   _refreshPageData();
			       });
		});
	}
	
	$scope.add = function(){
		$scope.modalTitle="Add New Customer";
		$scope.btnSave = true;
		$scope.btnUpdate = false;
		_clearForm();
	} 
	$scope.edit = function(customer){
		$scope.modalTitle="Edit Customer";
		$scope.btnSave=false;
		$scope.btnUpdate = true;
		
		$scope.customer = {
				id:customer.id,
				customerCode:customer.customerCode,
				firstName:customer.firstName,
				lastName:customer.lastName,
				sex:customer.sex,
				phoneNumber:customer.phoneNumber,
				email:customer.email,
				address:customer.address,
				zipCode:customer.zipCode,
				
		};
		
	}
	
	function _clearForm(){
		$scope.customer = {
				id:" ",
				customerCode:" ",
				firstName:" ",
				lastName:" ",
				sex:" ",
				phoneNumber:" ",
				email:"",
				address:"",
				zipCode:"",
				dateEnter:""
		};
	}
	
});