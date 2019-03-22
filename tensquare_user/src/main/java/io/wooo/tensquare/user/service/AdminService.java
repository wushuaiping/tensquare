package io.wooo.tensquare.user.service;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.exception.BadRequestException;
import io.wooo.tensquare.user.entity.Admin;
import io.wooo.tensquare.user.rabbitmq.SmsMessage;
import io.wooo.tensquare.user.repository.AdminRepository;
import io.wooo.tensquare.user.util.SmsTemplateUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 管理员service
 *
 * @author: wushuaiping
 * @date: 2018/11/29 3:06 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;

    private BCryptPasswordEncoder encoder;

    private SmsMessage smsMessage;

    public void save(Admin admin) {

        // 检查手机号是否已存在
        if (checkMobile(admin.getMobile())) {
            throw new BadRequestException("该手机号已注册");
        }

        // 初始化admin
        admin.initAdmin(admin);
        // 加密密码
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        // 发送手机消息给管理员
        final String smsContent = SmsTemplateUtils.adminReg(admin.getUsername());
        smsMessage.sendMobileSms(admin.getMobile(), smsContent);
    }

    public Admin getAdminById(String id) {
        return adminRepository.getOne(id);
    }

    public PageResult<Admin> getAdminByPage(String username, Pageable pageable) {
        final Page<Admin> adminPage = adminRepository.findByUsernameLikeOrMobileLikeOrderByLastLoginDate(username, pageable);
        final PageResult<Admin> result = new PageResult<>();
        result.setTotal(adminPage.getTotalElements());
        result.setRows(adminPage.getContent());
        return result;
    }

    public boolean checkMobile(String mobile) {
        return adminRepository.findByMobile(mobile) != null;
    }

    public Admin login(String mobile, String password) {
        // 因为每次的加盐都是不一样的，所以通过手机号先拿出用户信息，然后再去匹配对象中的密码和用户输入的密码是否一致。
        final Admin admin = adminRepository.findByMobile(mobile);
        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }
}
