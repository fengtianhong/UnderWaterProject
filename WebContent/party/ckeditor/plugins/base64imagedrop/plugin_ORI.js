CKEDITOR.plugins.add( 'base64imagedrop', {
    init: function( editor ) {
    	
        function doNothing(e) {
        	console.log("donothing");
        }

        function dropHandler(e) {
            e.preventDefault();
            
            //Get dropped file
            var file = e.dataTransfer.files;
                        
            //Return if file is not an image
//            var fileType = file["type"];
//
//            var ValidImageTypes = ["image/gif", "image/jpeg", "image/png"];
//            if (ValidImageTypes.indexOf(fileType) < 0){
//            	return;
//            }
            //Read file as data url (base64) and insert it as an image
            for(var i in file){
            	if(file[i].type == 'image/jpeg' || file[i].type == 'image/gif' || file[i].type == 'image/png'){
                    var fr = new FileReader();
                    fr.onload = (function(f) { return function(e) {
        				insertImage(e.target.result);
        			}; })(file[i]);
                    fr.readAsDataURL(file[i]);
            	}
            }
        }

        function insertImage(source) {
            var elem = editor.document.createElement('img', {
                attributes: {
                    src: source
                }
            });
            editor.insertElement(elem);
        }

        CKEDITOR.on('instanceReady', function() {
        	var iframeBase = document.querySelector('iframe').contentDocument.querySelector('html');
            var iframeBody = iframeBase.querySelector('body');

            iframeBody.ondragover = doNothing;
            iframeBody.ondrop = dropHandler;

            paddingToCenterBody = ((iframeBase.offsetWidth - iframeBody.offsetWidth) / 2) + 'px';
            iframeBase.style.height = '100%';
            iframeBase.style.width = '100%';
            iframeBase.style.overflowX = 'hidden';

            iframeBody.style.height = '100%';
            iframeBody.style.margin = '0';
            iframeBody.style.paddingLeft = paddingToCenterBody;
            iframeBody.style.paddingRight = paddingToCenterBody;
        });
    }
});
