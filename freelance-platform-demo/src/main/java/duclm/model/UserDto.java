package duclm.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto{
	private Long userId;
	@NotEmpty(message = "Email cannot be empty")
	@Size(min = 5, max = 250)
	private String email;
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 5, max = 60)
	private String password;
	private String name;
	private String avatar;
	private MultipartFile avatarFile;
	private String phone;
	private String description="Write something about yourself...";
	private int role;
	private int status;
}
