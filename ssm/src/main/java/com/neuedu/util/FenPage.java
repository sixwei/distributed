package com.neuedu.util;


import com.neuedu.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FenPage {

    private int total;

    private List<User> users;
}
