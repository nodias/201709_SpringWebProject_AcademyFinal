function fileCheck() {
		var filebing = document.getElementById('file').value;
		
		var fileName = filebing.slice(filebing.indexOf(".") + 1).toLowerCase();
		document.write(fileName);
		if (fileName != "jpg" && fileName != "png" && fileName != "gif"
				&& fileName != "bmp") {
			alert('이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
			return;
		} else {
			fileName = filebing.substring(filebing.lastIndexOf("\\") + 1); 
// 			document.write(fileName);
			for (var i = 0; i < fileName.length; i++) {
				if (fileName.substring(i, i + 1) == " ") {
					alert("파일명에 공백을 쓸 수 없습니다.");
					return;
				} else{
// 					
				}
			}
		}
		document.trans2_frm.submit();
	}