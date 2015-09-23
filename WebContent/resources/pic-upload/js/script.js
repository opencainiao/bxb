/**
 * 
 * HTML5 Image uploader with Jcrop
 * 
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2012, Script Tutorials http://www.script-tutorials.com/
 */

var jcrop_api;

// convert bytes into friendly format
function bytesToSize(bytes) {
	var sizes = [ 'Bytes', 'KB', 'MB' ];
	if (bytes == 0)
		return 'n/a';
	var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
	return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};

// check for selected crop region
function checkForm() {
	if (parseInt($('#w').val()))
		return true;
	$('.error').html('Please select a crop region and then press Upload').show();
	return false;
};

// update info by cropping (onChange and onSelect events handler)
function updateInfo(e) {
	$('#x1').val(e.x);
	$('#y1').val(e.y);
	$('#x2').val(e.x2);
	$('#y2').val(e.y2);
	$('#w').val(e.w);
	$('#h').val(e.h);
};

// clear info by cropping (onRelease event handler)
function clearInfo() {
	$('.info #w').val('');
	$('.info #h').val('');
};

function fileSelectHandler() {

	// get selected file
	var oFile = $('#image_file')[0].files[0];

	var length = $("#image_file").val().length;
	console.log(length);
	$("#image_file").width(length * 8.5 + 90);

	// hide all errors
	$('.error').hide();

	// check for image type (jpg and png are allowed)
	var rFilter = /^(image\/jpeg|image\/png)$/i;
	if (!rFilter.test(oFile.type)) {
		$('.error').html('请选择图片文件 (jpg、png)').show();
		return;
	}

	// check for file size
	var max_size = 1.5 * 1024 * 1024;
	if (oFile.size > max_size) {

		var real_size = (oFile.size / 1024) / 1024;
		real_size = real_size.toFixed(2);

		$('.error').html('文件太大【' + real_size + 'M】，请选择小图片(1.5M以内)').show();
		return;
	}

	// preview element
	var oImage = document.getElementById('preview');

	// prepare HTML5 FileReader
	var oReader = new FileReader();
	oReader.onload = function(e) {

		// e.target.result contains the DataURL which we can use as a source of
		// the image
		oImage.src = e.target.result;
		// console.log(oImage.src);

		oImage.onload = function() { // onload event handler

			// display step 2
			$('.step2').fadeIn(500);

			// display some basic image info
			var sResultFileSize = bytesToSize(oFile.size);
			$('#filesize').val(sResultFileSize);
			$('#filetype').val(oFile.type);
			$('#filedim').val(oImage.naturalWidth + ' x ' + oImage.naturalHeight);

			// Create variables (in this scope) to hold the Jcrop API and image
			// size
			var boundx, boundy;

			// destroy Jcrop if it is existed
			if (jcrop_api) {
				jcrop_api.destroy();
				jcrop_api = null;
				$('#preview').width(oImage.naturalWidth);
				$('#preview').height(oImage.naturalHeight);
			}

			// initialize Jcrop
			$('#preview').Jcrop({
				minSize : [ 32, 32 ], // min crop size
				aspectRatio : 1, // keep aspect ratio 1:1
				bgFade : true, // use fade effect
				bgOpacity : .3, // fade opacity
				setSelect : [ 50, 0, 180, 180 ],
				onChange : updateInfo,
				onSelect : updateInfo,
				onRelease : clearInfo
			}, function() {
				// use the Jcrop API to get the real image size
				var bounds = this.getBounds();
				//boundx = bounds[0];
				//boundy = bounds[1];
				
				boundx =oImage.naturalWidth;
				boundy = oImage.naturalHeight;

				console.log(boundx + "---" + boundy);

				jcrop_api = this;
			});
		};

	};

	// read selected file as DataURL
	oReader.readAsDataURL(oFile);
}