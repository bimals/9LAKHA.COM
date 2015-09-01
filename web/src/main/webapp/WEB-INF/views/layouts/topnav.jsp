<div class="span2 well margin-bottom-none">
	<div class="container margin fill">
		<div class="row" ng-controller="searchController">
			<div class="col-lg-6">
				<form class="form-horizontal centered">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..." ng-model="searchText"> <span
							class="input-group-btn">
							<button class="btn btn-default" ng-click="search(3)">Go!</button>
						</span>
					</div>
				</form>
			</div>
			<div class="col-lg-4 col-md-4">
					<button class="btn btn-default" ng-click="startSelling()">Start Selling Now!</button>
			</div>
		</div>
	</div>
</div>