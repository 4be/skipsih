if (location.pathname.substr(0, 5) == "/hcms" && localStorage.getItem("token") == null) {
    location.href = "/";
}

