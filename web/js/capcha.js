function refresh() {
            $.ajax({
            type: "GET",
            url: "/swp/CaptchaImage", // Đặt tên đúng đường dẫn của servlet trên máy chủ của bạn
            success: function() {
                // Gọi thành công - bạn có thể thực hiện các hành động cần thiết sau khi làm mới hình ảnh CAPTCHA
                console.log("Captcha refreshed successfully");
                var captchaImage = document.querySelector('.captcha img');
                var captchaInput = document.querySelector('input[name="captcha"]');
                // Tạo một thời gian (timestamp) mới để ngăn cache và làm mới hình ảnh CAPTCHA
                var timestamp = new Date().getTime();

                // Cập nhật thuộc tính src với timestamp mới
                captchaImage.src = '/swp/CaptchaImage?' + timestamp;
                
                // Clear the captcha input field
                captchaInput.value = '';
                // Focus on the captcha input field after clearing the value
                captchaInput.focus();
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi khi request không thành công
                console.error("Error refreshing captcha:", error);
            }
        });
    }

