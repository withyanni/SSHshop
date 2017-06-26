package adminuser.service;

import org.springframework.transaction.annotation.Transactional;
import adminuser.dao.AdminUserDao;
import adminuser.vo.AdminUser;

/**
 * 
 * @author yanni
 * @time   2017年6月5日下午9:57:49
 */
@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
