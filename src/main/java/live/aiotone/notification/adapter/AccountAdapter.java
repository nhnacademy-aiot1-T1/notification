package live.aiotone.notification.adapter;

import com.nhnacademy.common.dto.CommonResponse;
import live.aiotone.notification.dto.AccountInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "account-service", path = "/api/account")
public interface AccountAdapter {
    @GetMapping("/users")
    public CommonResponse<List<AccountInfo>> getAccountList();
}
